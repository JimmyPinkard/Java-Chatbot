import org.json.JSONArray;
import org.json.JSONObject;
import org.json.*;

import java.util.Map;
import java.util.Set;

public class Rep
{
    private String personalInfo;
    private String address;
    private String contactInfo;
    private String committees;
    private final JSONObject asJSON;

    public Rep(final JSONObject json)
    {
        this.asJSON = json;
        setPersonalInfo();
        setAddress();
        setContactInfo();
        setCommittees();
    }

    public String getPersonalInfo()
    {
        return personalInfo;
    }

    private void setPersonalInfo()
    {
        JSONObject json = asJSON.getJSONObject("personalInfo");
        this.personalInfo = buildString(json);
    }

    public String getAddress()
    {
        return address;
    }

    private void setAddress()
    {
        JSONObject json = asJSON.getJSONObject("addresses");
        this.address = buildString(json);
    }

    public String getContactInfo()
    {
        return contactInfo;
    }

    private void setContactInfo()
    {
        JSONObject json = asJSON.getJSONObject("contactInfo");
        this.contactInfo = buildString(json);
    }

    public String getCommittees()
    {
        return committees;
    }

    private void setCommittees()
    {
        JSONArray array = asJSON.getJSONArray("committees");
        this.committees = buildString(array);
    }

    private String buildString(JSONObject json)
    {
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<String, Object>> entries = json.toMap().entrySet();
        for(Map.Entry<String, Object> entry : entries)
        {
            builder.append(entry.getKey()).append(": ").append(entry.getValue()).append('\n');
        }
        return builder.toString();
    }

    private String buildString(JSONArray json)
    {
        StringBuilder builder = new StringBuilder();
        Object[] entries = json.toList().toArray();
        for(int i = 0; i < entries.length - 1; ++i)
        {
            builder.append(entries[i]).append(", ");
            if((i + 1) % 3 == 0)
            {
                builder.append('\n');
            }
        }
        return builder.append(entries[entries.length - 1]).toString();
    }

    @Override
    public String toString()
    {
        return personalInfo + '\n' + address + '\n' + contactInfo + '\n' + committees;
    }
}