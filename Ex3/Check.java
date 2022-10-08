/** Основной класс с методом main
 @author p.braer
 */

public class Check {

    /** Проверка работы остальных классов и обработка ошибок */
    public static void main(String[] args) throws MyExceptions {
        Record record1 = new Record(1, "89132837783", "Катя");
        Record record2 = new Record(2, "89133757398", "Ира");
        Record record3 = new Record(3, "+79133708198", "катя");
        Record record4 = new Record(4, "abc", "Ваня");
        Record record5 = new Record(5, "+79133708198", "Лена");
        Record record6 = new Record(6, "+79133720392", "Дима");
        Record record7 = new Record(7, "+79138993312", "");
        PhoneBook MyBook = new PhoneBook();

        MyBook.createRecord(record1);
        MyBook.createRecord(record2);
        MyBook.createRecord(record3);
        MyBook.createRecord(record5);
        MyBook.createRecord(record6);

        // проверка ошибки PhoneNumberNotCorrect
        try{
            MyBook.createRecord(record4);
        }
        catch(MyExceptions e) {
            System.out.println(e.getMessage());
        }

        MyBook.getMyRecords();
        MyBook.deleteRecord(5);

        // проверка ошибки PhoneNumberAlreadyExists
        try{
            MyBook.createRecord(record1);
        }
        catch(MyExceptions e) {
            System.out.println(e.getMessage());
        }

        // Исключение RecordNotValid
        try {
            MyBook.updateRecord(record7);
        }catch(MyExceptions e) {
            System.out.println(e.getMessage());}

        // RecordNotFound непроверяемый
        MyBook.updateRecord(new Record(100, "89132754883", "Петр"));
        MyBook.deleteRecord(180);

        MyBook.getMyRecords();


    }
}