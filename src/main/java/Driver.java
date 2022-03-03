import org.json.JSONObject;

import java.util.Arrays;
import java.util.Scanner;

public class Driver
{
    private final Scanner in = new Scanner(System.in);
    private final String[] options = new String[]{"Tell me about the rep", "Where does the rep live", "How do I contact my rep",
            "What committees is my rep on", "Tell me everything", "Help", "Bye"};
    private void run()
    {
        final IO io = IO.getInstance();
        final JSONObject json = new JSONObject(io.readFile("data/mia.json"));
        Rep rep = new Rep(json);
        eventLoop(rep);
        goodbye();
    }

    private void eventLoop(final Rep rep)
    {
        int fails = 0;
        while(true)
        {
            final String query = getInput("What would you like to ask?");
            int index = isApproved(query);
            if(index >= 0)
            {
                fails = 0;
                System.out.println(answer(index, rep));
            }
            else
            {
                ++fails;
                if(fails == 3)
                {
                    System.err.println("You asked three bad questions in a row.");
                    goodbye();
                }
                System.err.println("You must use one of the approved queries.");
                showOptions();
            }
        }
    }

    private int isApproved(final String query)
    {
        int i = 0;
        for(final String option : options)
        {
            if(option.equalsIgnoreCase(query))
            {
                return i;
            }
            ++i;
        }
        return -1;
    }

    private void showOptions()
    {
        for(final String option : options)
        {
            System.out.println(option);
        }
    }

    private String getInput(final String prompt)
    {
        System.out.println(prompt);
        return in.nextLine();
    }

    private String answer(final int index, final Rep rep)
    {
        Object data = switch (index) {
            case 0 -> rep.getPersonalInfo();
            case 1 -> rep.getAddress();
            case 2 -> rep.getContactInfo();
            case 3 -> rep.getCommittees();
            case 4 -> rep.toString();
            case 5 -> Arrays.toString(options);
            default -> new Object();
        };
        if(index == 6)
        {
            goodbye();
        }
        return data.toString();
    }

    private void goodbye()
    {
        in.close();
        System.out.println("Goodbye");
        System.exit(0);
    }

    public static void main(final String[] args)
    {
        new Driver().run();
    }
}
