import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Spyware
{
    private static Spyware spyware;
    List<String> queries;
    JSONObject options;
    private Spyware()
    {
        queries = new ArrayList<>();
        options = new JSONObject();
    }

    public static Spyware getInstance()
    {
        if(spyware == null)
        {
            spyware = new Spyware();
        }
        return spyware;
    }

    public void addQueries(final String query)
    {
        try
        {
            options.put(query, (Integer) options.get(query) + 1);
        }
        catch (Exception e)
        {
            options.put(query, 1);
        }
    }

    public void report()
    {
        System.out.println("Usage statistics.");
        for(Map.Entry<String, Object> entry : options.toMap().entrySet())
        {
            System.out.printf("Queried: %s, %d times\n", entry.getKey(), (Integer)entry.getValue());
        }
    }
}
