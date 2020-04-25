import au.com.bytecode.opencsv.CSVReader;
import models.DigitalFrame;
import models.PhotoFrame;
import models.PlainFrame;
import services.DigitalFrameService;
import services.PhotoFrameService;
import services.PlainFrameService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class logic_hibernate {


    // for Data Base
    static final String USER = "root";
    static final String PASS = "";
    static final String HOST = "localhost:3307";


    // read csv file
    public static List<String[]> readCSV(String filePath) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(filePath), ',', '"', 0);
        return reader.readAll();
    }

    //Add field in database
    public static void addField(String File) throws IOException, SQLException {
        logic_hibernate.readCSV(File);
        PhotoFrameService photoFrameService = new PhotoFrameService();
        DigitalFrameService digitalFrameService = new DigitalFrameService();
        PlainFrameService plainFrameService = new PlainFrameService();

        PhotoFrame photoFrame = new PhotoFrame();
        DigitalFrame digitalFrame = new DigitalFrame();
        PlainFrame plainFrame = new PlainFrame();
        for (String[] row : logic_hibernate.readCSV(File)) {
            for (String a : row) {
                String[] k = a.split(";");
                photoFrame.setName(k[0]);
                photoFrame.setPrice(k[1]);
                photoFrame.setColor(k[2]);
                photoFrame.setType(k[3]);
                if (k[3].equals("digital")) {
                    digitalFrame.setMemory(k[4]);
                    digitalFrame.setViewing_angle(k[5]);
                    digitalFrame.setSize(k[6]);
                    digitalFrame.setPhotoFrame(photoFrame);
                    photoFrame.setDigitalFrame(digitalFrame);
                    photoFrameService.add(photoFrame);
                    digitalFrameService.add(digitalFrame);
                } else if (k[3].equals("plain")) {
                    plainFrame.setMaterial(k[4]);
                    plainFrame.setWidth(k[5]);
                    plainFrame.setMaterial_insert(k[6]);
                    plainFrame.setPhotoFrame(photoFrame);
                    photoFrame.setPlainFrame(plainFrame);
                    photoFrameService.add(photoFrame);
                    plainFrameService.add(plainFrame);
                }
            }
        }
    }


    //this default method
    public static void main() throws SQLException {
        boolean status = true;
        while (status) {
            Scanner in = new Scanner(System.in);
            System.out.print("При запуске программы в базе данных создаются таблицы и добавляются записи. ");
            System.out.println("Выберите дальнейшее действие:");
            System.out.println("'update' - Обновление данных по id фото рамки ");
            System.out.println("'delete' - удаление данных по выбранному id фоторамки");
            System.out.println("'select' - выборка всех данных из бд ");
            System.out.println("'select_id' - выборка данных по id");
            System.out.println("'stop' - заверешение программы");
            System.out.print("Input method: ");
            String method = in.nextLine();
            switch (method) {
                case "update":
                    System.out.println("this update method");
                    logic_hibernate.updatePhotoframe();
                    break;
                case "delete":
                    deleteMethod();
//                    System.out.println("this delete method");
                    break;
                case "select":
                    selectAll();
//                    System.out.println("this select method");
                    break;
                case "select_id":
                    selectById();
//                    System.out.println("this select id method");
                    break;
                case "stop":
                    status = false;
                    break;
                default:
                    System.out.println("Повторите попытку или нажмити 0 для завершения работы программы");
                    break;
            }
        }
    }

    public static void updatePhotoframe() throws SQLException {
        // сделать select данных, что бы пользователь видел данные
        // пользователь выбирает id
        // запрос на выборку данных по id
        // пользователь выбирает какое поле хочет изменить кроме id
        //елси пользователь изменил поле type c digital на plain или наоборот, то перезаписать данные в бд
        PhotoFrameService photoFrameService = new PhotoFrameService();
        DigitalFrameService digitalFrameService = new DigitalFrameService();
        PlainFrameService plainFrameService = new PlainFrameService();
        photoFrameService.getAll();
        boolean status = true;
        while (status) {
            System.out.println("выберите id фоторамки:");
            Scanner in = new Scanner(System.in);
            int id = in.nextInt();
            while (status) {
                System.out.println("Выберите поле которое хотите изменитьЖ");
                Scanner inn = new Scanner(System.in);
                System.out.print("Input method: ");
                String method = inn.nextLine();
                if (photoFrameService.getById(id).getType().equals("digital")) {
                    System.out.println(digitalFrameService.getByIdPhotoFrame(id));
                    switch (method) {
                        case "name":
                            //method update name
                            updateName(id);
                            System.out.println("update name");
                            break;
                        case "price":
                            //method update price
                            updatePrice(id);
                            System.out.println("update price");
                            break;
                        case "color":
                            //method update color
                            updateColor(id);
                            System.out.println("update color");
                            break;
                        case "memory":
                            //method update Memory
                            updateMemory(id);
                            System.out.println("update memory");
                            break;
                        case "size":
                            //method update size
                            updateSize(id);
                            System.out.println("update size");
                            break;
                        case "viewing angle":
                            //method update viewing angle
                            updateViewing_Angle(id);
                            System.out.println("update viewing angle");
                            break;
                        case "stop":
                            status = false;
                            break;
                        default:
                            System.out.println("Повторите попытку или нажмити 0 для завершения работы программы");
                            break;
                    }
                } else {
                    System.out.println(plainFrameService.getByIdPhotoFrame(id));
                    switch (method) {
                        case "name":
                            //method update name
                            updateName(id);
                            System.out.println("update name");
                            break;
                        case "price":
                            //method update price
                            updatePrice(id);
                            System.out.println("update price");
                            break;
                        case "color":
                            //method update color
                            updateColor(id);
                            System.out.println("update color");
                            break;
                        case "width":
                            //method update width
                            updateWidth(id);
                            System.out.println("update width");
                            break;
                        case "material_insert":
                            //method update material_insert
                            updateMaterial_insert(id);
                            System.out.println("update material_insert");
                            break;
                        case "material":
                            //method update  material
                            updateMaterial(id);
                            System.out.println("update material");
                            break;
                        case "stop":
                            status = false;
                            break;
                        default:
                            System.out.println("Повторите попытку или нажмити 0 для завершения работы программы");
                            break;
                    }
                }
            }
        }
    }


    public static void updateName(int id) throws SQLException {
        PhotoFrame photoFrame = new PhotoFrame();
        PhotoFrameService photoFrameService = new PhotoFrameService();
        System.out.println("введите другой name:");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        photoFrame.setId(id);
        photoFrame.setName(name);
        photoFrame.setColor(photoFrameService.getById(id).getColor());
        photoFrame.setPrice(photoFrameService.getById(id).getPrice());
        photoFrame.setType(photoFrameService.getById(id).getType());
        photoFrameService.update(photoFrame);
    }

    public static void updateColor(int id) throws SQLException {
        PhotoFrame photoFrame = new PhotoFrame();
        PhotoFrameService photoFrameService = new PhotoFrameService();
        System.out.println("введите другой color:");
        Scanner in = new Scanner(System.in);
        String color = in.nextLine();
        photoFrame.setId(id);
        photoFrame.setName(photoFrameService.getById(id).getName());
        photoFrame.setColor(color);
        photoFrame.setPrice(photoFrameService.getById(id).getPrice());
        photoFrame.setType(photoFrameService.getById(id).getType());
        photoFrameService.update(photoFrame);
    }

    public static void updatePrice(int id) throws SQLException {
        PhotoFrame photoFrame = new PhotoFrame();
        PhotoFrameService photoFrameService = new PhotoFrameService();
        System.out.println("введите другой price:");
        Scanner in = new Scanner(System.in);
        String price = in.nextLine();
        photoFrame.setId(id);
        photoFrame.setName(photoFrameService.getById(id).getName());
        photoFrame.setColor(photoFrameService.getById(id).getColor());
        photoFrame.setPrice(price);
        photoFrame.setType(photoFrameService.getById(id).getType());
        photoFrameService.update(photoFrame);
    }


    public static void updateSize(int id) throws SQLException {
        DigitalFrameService digitalFrameService = new DigitalFrameService();
        DigitalFrame digitalFrame = new DigitalFrame();
        System.out.println("введите другой size:");
        Scanner in = new Scanner(System.in);
        String size = in.nextLine();
        digitalFrame.setId(digitalFrameService.getByIdPhotoFrame(id).getId());
        digitalFrame.setSize(size);
        digitalFrame.setViewing_angle(digitalFrameService.getByIdPhotoFrame(id).getViewing_angle());
        digitalFrame.setMemory(digitalFrameService.getByIdPhotoFrame(id).getMemory());
        digitalFrameService.update(digitalFrame);
    }

    public static void updateMemory(int id) throws SQLException {
        DigitalFrameService digitalFrameService = new DigitalFrameService();
        DigitalFrame digitalFrame = new DigitalFrame();
        System.out.println("введите другой memory:");
        Scanner in = new Scanner(System.in);
        String memory = in.nextLine();
        digitalFrame.setId(digitalFrameService.getByIdPhotoFrame(id).getId());
        digitalFrame.setSize(digitalFrameService.getByIdPhotoFrame(id).getSize());
        digitalFrame.setViewing_angle(digitalFrameService.getByIdPhotoFrame(id).getViewing_angle());
        digitalFrame.setMemory(memory);
        digitalFrameService.update(digitalFrame);
    }

    public static void updateViewing_Angle(int id) throws SQLException {
        DigitalFrameService digitalFrameService = new DigitalFrameService();
        DigitalFrame digitalFrame = new DigitalFrame();
        System.out.println("введите другой viewing angle:");
        Scanner in = new Scanner(System.in);
        String viewing_angle = in.nextLine();
        digitalFrame.setId(digitalFrameService.getByIdPhotoFrame(id).getId());
        digitalFrame.setSize(digitalFrameService.getByIdPhotoFrame(id).getSize());
        digitalFrame.setViewing_angle(viewing_angle);
        digitalFrame.setMemory(digitalFrameService.getByIdPhotoFrame(id).getViewing_angle());
        digitalFrameService.update(digitalFrame);
    }


    public static void updateWidth(int id) throws SQLException {
        PlainFrameService plainFrameService = new PlainFrameService();
        PlainFrame plainFrame = new PlainFrame();
        System.out.println("введите другой width:");
        Scanner in = new Scanner(System.in);
        String width = in.nextLine();
        plainFrame.setId(plainFrameService.getByIdPhotoFrame(id).getId());
        plainFrame.setWidth(width);
        plainFrame.setMaterial(plainFrameService.getByIdPhotoFrame(id).getMaterial());
        plainFrame.setMaterial_insert(plainFrameService.getByIdPhotoFrame(id).getMaterial_insert());
        plainFrameService.update(plainFrame);
    }

    public static void updateMaterial(int id) throws SQLException {
        PlainFrameService plainFrameService = new PlainFrameService();
        PlainFrame plainFrame = new PlainFrame();
        System.out.println("введите другой Material:");
        Scanner in = new Scanner(System.in);
        String Material = in.nextLine();
        plainFrame.setId(plainFrameService.getByIdPhotoFrame(id).getId());
        plainFrame.setWidth(plainFrameService.getByIdPhotoFrame(id).getWidth());
        plainFrame.setMaterial(Material);
        plainFrame.setMaterial_insert(plainFrameService.getByIdPhotoFrame(id).getMaterial_insert());
        plainFrameService.update(plainFrame);
    }

    public static void updateMaterial_insert(int id) throws SQLException {
        PlainFrameService plainFrameService = new PlainFrameService();
        PlainFrame plainFrame = new PlainFrame();
        System.out.println("введите другой Material insert:");
        Scanner in = new Scanner(System.in);
        String Material_insert = in.nextLine();
        plainFrame.setId(plainFrameService.getByIdPhotoFrame(id).getId());
        plainFrame.setWidth(plainFrameService.getByIdPhotoFrame(id).getWidth());
        plainFrame.setMaterial(plainFrameService.getByIdPhotoFrame(id).getMaterial_insert());
        plainFrame.setMaterial_insert(Material_insert);
        plainFrameService.update(plainFrame);
    }

    public static void deleteMethod() throws SQLException {
        PhotoFrameService photoFrameService = new PhotoFrameService();
        DigitalFrameService digitalFrameService = new DigitalFrameService();
        PlainFrameService plainFrameService = new PlainFrameService();

        PhotoFrame photoFrame = new PhotoFrame();
        DigitalFrame digitalFrame = new DigitalFrame();
        PlainFrame plainFrame = new PlainFrame();
        photoFrameService.getAll();
        System.out.println("Выберите id фоторамки, которую хотите удалить: ");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();
        if (photoFrameService.getById(id).getType().equals("digital")) {

            digitalFrame.setId(digitalFrameService.getByIdPhotoFrame(id).getId());
            photoFrame.setId(photoFrameService.getById(id).getId());
            digitalFrameService.remove(digitalFrame);
            photoFrameService.remove(photoFrame);
        } else {
            plainFrame.setId(plainFrameService.getByIdPhotoFrame(id).getId());
            photoFrame.setId(photoFrameService.getById(id).getId());
            plainFrameService.remove(plainFrame);
            photoFrameService.remove(photoFrame);
        }
    }


    public static void selectAll() throws SQLException {
        PhotoFrameService photoFrameService = new PhotoFrameService();
        photoFrameService.getAll();
    }

    public static void selectById() throws SQLException {
        PhotoFrameService photoFrameService = new PhotoFrameService();
        DigitalFrameService digitalFrameService = new DigitalFrameService();
        PlainFrameService plainFrameService = new PlainFrameService();
        photoFrameService.getAll();
        System.out.println("Выберите id фоторамки, которую хотите удалить: ");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();
        if (photoFrameService.getById(id).getType().equals("digital")) {
            digitalFrameService.getByIdPhotoFrame(id);
        } else {
            plainFrameService.getByIdPhotoFrame(id);
        }
    }


}
