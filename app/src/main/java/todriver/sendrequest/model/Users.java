package todriver.sendrequest.model;

public class Users {

   public float rotation;
   public String name;
   public String image;
   public String status;
   public String request;


    public Users() {
    }


    public String getRequest() {
        return request;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public Users(float rotation) {
        this.rotation = rotation;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }


}
