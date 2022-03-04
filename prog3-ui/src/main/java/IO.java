import java.io.File;
import java.util.Scanner;

public class IO
{
    private static IO instance = null;
    private IO()
    {
    }

    public static IO getInstance()
    {
        if(instance == null)
        {
            instance = new IO();
        }
        return instance;
    }

    public String readFile(final String path)
    {
        StringBuilder result = new StringBuilder();
        File file = new File(path);
        try
        {
            Scanner in = new Scanner(file);
            while (in.hasNextLine())
            {
                result.append(in.nextLine());
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return result.toString();
    }
}
