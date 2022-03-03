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
        eventLoop(json);
        goodbye();
    }

    private void eventLoop(final JSONObject json)
    {
        while(true)
        {
            final String query = getInput("What would you like to ask?");
            int index = isApproved(query);
            if(index >= 0)
            {
                answer(index, json);
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

    private void answer(final int index, final JSONObject json)
    {
        Object data;
        switch (index)
        {
            case 0: data = json.get("personalInfo").toString(); break;
            case 1: data = json.get("addresses").toString(); break;
            case 2: data = json.get("contactInfo").toString(); break;
            case 3: data = json.get("committees").toString(); break;
            case 4: data = json.toString(); break;
            case 5: data = Arrays.toString(options); break;
            case 6: goodbye(); return;
            default: showOptions(); return;
        }
        System.out.println(data.toString());
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
