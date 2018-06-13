package com.example.ankit.vehicle_mechanic;

public class Actors {



    private  String garage_id;
    private  String image;
    private  String name;
    private  String description;
    private  String rating;
    private  String area;


    private String brand;
    private String model_no;
    private String reg_no;
    private String purchase_year;
    private String km;

    private  String vehicle_name;
    private  String status;




    private  String Completed;



    private String service_name;
    private String service_description;



    private String service_amount;




    public Actors() {
        // TODO Auto-generated constructor stub
    }



    public Actors( String name,String garage_id,String image,String description,String rating,String area,String service_name,String service_description,String service_image,String service_id,String service_amount,String status,String vehicle_name, String Completed, String brand,
             String model_no, String reg_no, String purchase_year, String km) {

        super();


        this.garage_id=garage_id;
        this.name=name;
        this.image=image;
        this.description=description;
        this.rating=rating;
        this.area=area;
        this.service_description=service_description;
        this.service_name=service_name;
        this.Completed=Completed;
        this.brand=brand;
        this.model_no=model_no;
        this.reg_no=reg_no;
        this.purchase_year=purchase_year;
        this.km=km;


    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getGarage_id() {
        return garage_id;
    }

    public void setGarage_id(String garage_id) {
        this.garage_id = garage_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_description() {
        return service_description;
    }

    public void setService_description(String service_description) {
        this.service_description = service_description;
    }

    public String getService_amount() {
        return service_amount;
    }

    public void setService_amount(String service_amount) {
        this.service_amount = service_amount;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompleted() {
        return Completed;
    }

    public void setCompleted(String completed) {
        Completed = completed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel_no() {
        return model_no;
    }

    public void setModel_no(String model_no) {
        this.model_no = model_no;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getPurchase_year() {
        return purchase_year;
    }

    public void setPurchase_year(String purchase_year) {
        this.purchase_year = purchase_year;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

}
