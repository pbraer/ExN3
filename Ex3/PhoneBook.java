/** Класс телефонных справочников
 * Дополнительно к заданию добавлено проверяемое исключение PhoneNumberNotCorrect - корректность набранного номера
 @author p.braer
 */

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {

    public ArrayList<Record> records= new ArrayList<Record>();

    /** Метод сохраняет в справочнике новую запись
     * PhoneNumberAlreadyExists - номер уже существует
     * PhoneNumberNotCorrect (доп) - номер имеет неправильный размер
     */
    public void createRecord(Record record) throws MyExceptions{

        if (records.contains(record)){
            throw new MyExceptions("PhoneNumberAlreadyExists");
        }else if (record.getPhoneNumber().length() == 12 || record.getPhoneNumber().length() == 11) {
            records.add(record);
        }else{
            throw new MyExceptions("PhoneNumberNotCorrect");
        }
    }

    /** Метод удаляет в справочнике запись по ID*/
    public void deleteRecord(long id){

        try{
            records.remove((int) (id - 2));
            System.out.println("Контакт #" + id + " удален.\n");
        }catch(IndexOutOfBoundsException e){new MyExceptions("RecordNotFound");}
    }

    /** Метод обновляет запись в справочнике
     * RecordNotFound - запись с таким ID не существует
     * RecordNotValid - новая запись заполнена не полностью
     * PhoneNumberNotCorrect (доп) - номер имеет неправильный размер
     */
    public void updateRecord(Record record) throws MyExceptions{

        try{
            if (record.getPhoneNumber().equals("") || record.getName().equals("")) {
                throw new MyExceptions("RecordNotValid");
            }else if (record.getPhoneNumber().length() == 12 || record.getPhoneNumber().length() == 11) {
                    records.remove((int) record.getId() - 1);
                    this.createRecord(record);
                    System.out.println("Контакт #" + record.getId() + " обновлен.\n");
                } else {
                    throw new MyExceptions("PhoneNumberNotCorrect");
                }
            }catch (IndexOutOfBoundsException e) {new MyExceptions("RecordNotFound");}
    }

    /** Метод получает список всех контактов
     */
    public List<Record> getAllRecords(){
        List<Record> allRec = new ArrayList<Record>();
        for (int i = 0; i < records.size(); i++) {
            allRec.add(records.get(i));
        }
        return allRec;
    }

    /** Метод выводит список всех контактов на экран в форматированном виде
     (1 столбец - номер ID, если что-то пропущено, то ячейка пуста)
     * Если существует несколько контактов с одним и тем же именем, они объединяются
     */
    public void getMyRecords(){
        List<String> done = new ArrayList<String>();

        System.out.printf("ID Имя          Номер\n----------------------------\n");

        for (int i = 0; i < this.getAllRecords().size(); i++) {

            if (done.contains(this.getAllRecords().get(i).getName().toLowerCase())) {
                continue;
            } else {
                done.add(this.getAllRecords().get(i).getName().toLowerCase());
            }
            System.out.print((i + 1) + ". " + this.getAllRecords().get(i).getName().toUpperCase());
            int p = 0;
            while (p <= 25 - this.getAllRecords().get(i).getPhoneNumber().length()
                    - this.getAllRecords().get(i).getName().length() - (i + "").length()) {
                System.out.print(" ");
                p++;
            }
            System.out.println(this.getAllRecords().get(i).getPhoneNumber());
            for (int t = 0; t < this.getAllRecords().size(); t++) {
                if (i == t) {
                    continue;
                } else {
                    if (this.getAllRecords().get(i).getName().toLowerCase().equals(this.getAllRecords().get(t).getName().toLowerCase())) {
                        int j = 0;
                        while (j <= 27 - this.getAllRecords().get(t).getPhoneNumber().length()) {
                            System.out.print(" ");
                            j++;
                        }
                        System.out.println(this.getAllRecords().get(t).getPhoneNumber());
                    }
                }
            }
        }
        System.out.println();
    }

}
