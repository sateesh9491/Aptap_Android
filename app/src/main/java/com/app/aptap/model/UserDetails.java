package com.app.aptap.model;

/**
 * Created by Jaison on 16/11/16.
 */

public class UserDetails
{

    private Details details;

    public Details getDetails ()
    {
        return details;
    }

    public void setDetails (Details details)
    {
        this.details = details;
    }

    public class Details
    {
        private String id;

        private String updated_at;

        private String email;

        private String name;

        private String created_at;

        private String Qrid;
        private String uniqueId;


        public String getQrid ()
        {
            return Qrid;
        }

        public void setQrid(String Qrid)
        {
            this.Qrid = Qrid;
        }

        public String getuniqueId ()
        {
            return uniqueId;
        }

        public void setuniqueId(String uniqueId)
        {
            this.uniqueId = uniqueId;
        }



        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getUpdated_at ()
        {
            return updated_at;
        }

        public void setUpdated_at (String updated_at)
        {
            this.updated_at = updated_at;
        }

        public String getEmail ()
        {
            return email;
        }

        public void setEmail (String email)
        {
            this.email = email;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        public String getCreated_at ()
        {
            return created_at;
        }

        public void setCreated_at (String created_at)
        {
            this.created_at = created_at;
        }
    }
}
