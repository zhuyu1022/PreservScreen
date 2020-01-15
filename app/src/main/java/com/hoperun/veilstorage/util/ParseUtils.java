package com.hoperun.veilstorage.util;



import com.google.gson.Gson;

public class ParseUtils
{
    
    public static <T> T parse(String json, Class<T> cla)
        throws Exception
    {
        try
        {
            
            if (json != null)
            {
                Gson gson = new Gson();
                T response = (T)gson.fromJson(json, cla);
                return response;
            }
        }
        catch (Exception e)
        { e.printStackTrace();
            // TODO: handle exception
           // throw new MIPException(ExceptionConst.EXCEPTION, e.toString());
        }
        return null;
    }
}