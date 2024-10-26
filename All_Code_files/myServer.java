
import com.vmm.JHTTPServer;
import java.io.IOException;
import java.util.Properties;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class myServer extends JHTTPServer
{ 
    public myServer(int port) throws IOException 
    {
        super(port);
    }

    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) 
    {
        Response res = null;
        
    if(uri.equals("/"))
    {
        String ans = Math.random()+"";
       
        res = new Response(HTTP_OK, "text/plain", ans);
        
       
    }
    else if(uri.equals("/one"))
    {
        String ans = Math.random()+"";
       
        res = new Response(HTTP_OK, "text/plain", ans);
        
        
    }
    
    else if(uri.equals("/userlogin"))
    {
        
        String username = parms.getProperty("username");
        String password = parms.getProperty("password");
        System.out.println("----->"+username+"---->"+password);
        try {
            
       
        
        ResultSet rs = DBLoader.executeQuery("select * from users where useremail='"+username+"' and password = '"+password+"'");
        if(rs.next())
        {
            res = new Response(HTTP_OK, "text/plain", "success");
        }
        else
        {
             res = new Response(HTTP_OK, "text/plain", "fail");
        }
         }
        catch (Exception e) 
         {
           e.printStackTrace();
         }
    }
    
    else if(uri.equals("/userverify"))
    {
        String email = parms.getProperty("email");
        ResultSet rs = DBLoader.executeQuery("select * from users where useremail='"+email+"'");
            try 
            {
                if(rs.next())
                {
                    res= new Response(HTTP_OK, "text/plain","exist");
                }
                else
                {
                    String otp = (int) (1000+(9999-1000)*Math.random())+"";
                    senderemail obj = new senderemail(email,"hello","Your Otp is: "+otp);
//                    GLOBAL.otp=otp;
//                    System.out.println(GLOBAL.otp);
                    res = new Response(HTTP_OK, "text/plain",otp);
                }   
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        
    }
    
    else if(uri.equals("/usersignup"))
    {
        String email = parms.getProperty("email");
        String password = parms.getProperty("pass");
        String mobile = parms.getProperty("mobile");
        String address = parms.getProperty("address");
        
        String name = saveFileOnServerWithRandomName(files, parms, "f1", "src/uploads");
        try {
        
            ResultSet rs1=DBLoader.executeQuery("select * from users where mobile='"+mobile+"'");
            if(rs1.next())
            {
                res=new Response(HTTP_OK,"text/plain","mobile");
                
            }
            else
            {
                rs1.moveToInsertRow();
                rs1.updateString("useremail", email);
                rs1.updateString("password", password);
                rs1.updateString("mobile", mobile);
                rs1.updateString("address", address);
                rs1.updateString("photo", "src/uploads/"+name);
                rs1.insertRow();
                res = new Response(HTTP_OK, "text/plain", "success");
            }
            
        //}
      } catch (Exception e)
      {
      e.printStackTrace();
      }
    }
    else if(uri.equals("/fetchcat"))
    {
        String ans="";
        try 
        {
            ResultSet rs = DBLoader.executeQuery("select * from category");
            while(rs.next())
            {
               String name = rs.getString("name");
               String photo = rs.getString("photo");
               
               String row = name+"$"+photo;
               ans = ans+row+";;";
            }
           res = new Response(HTTP_OK, "text/plain", ans);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    else if(uri.equals("/AdminLogin"))
    {
        String name = parms.getProperty("name");
        String pass = parms.getProperty("pass");
        
        try {
            ResultSet rs = DBLoader.executeQuery("select * from Admin where username='"+name+"'and password='"+pass+"'");
            if(rs.next())
            {
                res = new Response(HTTP_OK, "text/plain", "success");
            }
            else
            {
                res = new Response(HTTP_OK, "text/plain", "failed");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    else if(uri.equals("/addcat"))
    {
        String category = parms.getProperty("category");
        String name = saveFileOnServerWithRandomName(files, parms, "f1", "src/uploads/");
        
        try {
            ResultSet rs = DBLoader.executeQuery("select * from category where name='"+category+"'");
            if(rs.next())
            {
                res = new Response(HTTP_OK, "text/plain", "exist");
            }
            else
            {
                rs.moveToInsertRow();
                rs.updateString("name", category);
                rs.updateString("photo", "src/uploads/"+name);
                rs.insertRow();
                
                res = new Response(HTTP_OK, "text/plain", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    else if(uri.equals("/fetchcategories"))
    {
        String ans="";
        try 
        {
            ResultSet rs = DBLoader.executeQuery("select name from category");
            while(rs.next())
            {
                String name = rs.getString("name");
                ans = ans+name+";;";
            }
            res = new Response(HTTP_OK, "text/plain", ans);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    else if(uri.equals("/addmovie"))
    {
        String category = parms.getProperty("category");
        String movie = parms.getProperty("movie");
        String director = parms.getProperty("director");
        String cast = parms.getProperty("cast");
        String id = parms.getProperty("id");
        String name = saveFileOnServerWithRandomName(files, parms, "f2", "src/uploads/");
        String movie_link = saveFileOnServerWithRandomName(files, parms,"movie_link" , "src/uploads/movies/");
        
        try {
            ResultSet rs = DBLoader.executeQuery("select * from movies");
            rs.moveToInsertRow();
            rs.updateString("movie_name", movie);
            rs.updateString("director", director);
            rs.updateString("cast",cast);
            rs.updateString("movie_link", "src/uploads/movies/"+movie_link);
            rs.updateString("trailer", id);
            rs.updateString("photo", "src/uploads/"+name);
            rs.updateString("category", category);
            rs.insertRow();
            res = new Response(HTTP_OK, "text/plain", "success");
        } 
        catch (Exception e) {
           res = new Response(HTTP_OK, "text/plain", e.toString());
        }
    }
    
    else if(uri.equals("/fetchmovie"))
    {
        String ans = "";
        String category = parms.getProperty("category");
        
        try {
            ResultSet rs = DBLoader.executeQuery("select * from movies where category='"+category+"'");
            while(rs.next())
            {
                int id = rs.getInt("id");
                String movie = rs.getString("movie_name");
                String photo = rs.getString("photo");
                String row = id+"$"+movie+"$"+photo;
                ans = ans+row+";;";
            }
            res = new Response(HTTP_OK, "text/plain", ans);
        }
        catch (Exception e) {
            res = new Response(HTTP_OK, "text/plain", e.toString());
        }
    }
    
    else if(uri.equals("/moviedetails"))
    {
        int id = Integer.parseInt(parms.getProperty("id"));
        
        try 
        {
           ResultSet rs = DBLoader.executeQuery("select * from movies where id="+id);
           if(rs.next())
           {
               String name = rs.getString("movie_name");
               String direct = rs.getString("director");
               String cast = rs.getString("cast");
               String youtube_id = rs.getString("trailer");
               String photo = rs.getString("photo");
               String movie_link = rs.getString("movie_link");
               String ans = name+"$"+direct+"$"+cast+"$"+photo+"$"+youtube_id+"$"+movie_link;
               res = new Response(HTTP_OK, "text/plain", ans);
           }
        } 
        catch (Exception e) {
            res = new Response(HTTP_OK, "text/plain", e.toString());
        }
    }
    
    else if(uri.equals("/allmovies"))
    {
        String name = parms.getProperty("movie");
        try {
            String ans="";
            ResultSet rs = DBLoader.executeQuery("select * from movies where movie_name like '"+name+"%'");
            while(rs.next())
            {
                String movie = rs.getString("movie_name");
                String photo = rs.getString("photo");
                String id = rs.getString("id");
                String row = movie+"$"+photo+"$"+id;
                ans = ans+row+";;";
            }
            res = new Response(HTTP_OK, "text/plain", ans);
        } 
        catch (Exception e) {
            res = new Response(HTTP_OK, "text/plain", e.toString());
        }
    }
    return res;
    }
    

}
