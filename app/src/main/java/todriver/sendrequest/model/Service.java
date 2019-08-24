package todriver.sendrequest.model;

public class Service {

    private Double from_lat;
    private Double from_lng;

    private Double to_lat;
    private Double to_lng;


    private String text_from;
    private String text_to;


    private String note;

    private String service_kind;

    private String service_price;


    private String name;
    private String Image;
    private String phone;
    private String email;








    public Service() {
    }

    public Double getFrom_lat() {
        return from_lat;
    }

    public void setFrom_lat(Double from_lat) {
        this.from_lat = from_lat;
    }

    public Double getFrom_lng() {
        return from_lng;
    }

    public void setFrom_lng(Double from_lng) {
        this.from_lng = from_lng;
    }

    public Double getTo_lat() {
        return to_lat;
    }

    public void setTo_lat(Double to_lat) {
        this.to_lat = to_lat;
    }

    public Double getTo_lng() {
        return to_lng;
    }

    public void setTo_lng(Double to_lng) {
        this.to_lng = to_lng;
    }

    public String getText_from() {
        return text_from;
    }

    public void setText_from(String text_from) {
        this.text_from = text_from;
    }

    public String getText_to() {
        return text_to;
    }

    public void setText_to(String text_to) {
        this.text_to = text_to;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getService_kind() {
        return service_kind;
    }

    public void setService_kind(String service_kind) {
        this.service_kind = service_kind;
    }

    public String getService_price() {
        return service_price;
    }

    public void setService_price(String service_price) {
        this.service_price = service_price;
    }
}
