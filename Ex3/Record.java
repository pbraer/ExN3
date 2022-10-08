/** Класс, содержащий контакты
 @author p.braer
 */

record Record(long id, String phoneNumber, String name) {

    public long getId() {
        return id;
    }
    public String getPhoneNumber() { return phoneNumber; }
    public String getName() { return name; }

}
