public class ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;

    //constructor
    public ticket(int row, int seat, double price, Person person){
        this.row=row;
        this.seat=seat;
        this.price=price;
        this.person=person;
    }

    //GETTERS
    public int getRow() {
        return row;
    }
    public int getSeat() {
        return seat;
    }
    public double getPrice() {
        return price;
    } 
    public Person getPerson() {
        return person;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    
    
    public void print(){
        System.out.println("Ticket Details\nName: " + person.getName() +
         "\nSurname: " + person.getSurname()+ "\nEmail: " +person.getEmail() + "\nRow Number: " +this.row + 
         "\nSeat number: " + this.seat + "\nPrice: " +this.price);
    }
}
