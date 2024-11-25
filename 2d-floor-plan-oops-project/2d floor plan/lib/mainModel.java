import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

class mainModel {

    frame f;

    static Array arr = new Array();
    rooms room = new rooms(arr);
    RoomObjects o = new RoomObjects(arr);
    filesave fs = new filesave(arr);

    public void getarray(Array array) {
        arr = array;
    }

    public static void main(String[] args) {

        mainModel m = new mainModel();
        m.f = new frame();
        m.f.setLayout(new BorderLayout());

        // SIDE PANNEL / SIDEBAR

        // INSTRUMENT PANNEL / TOPBAR /
        JPanel paneltop = new JPanel();
        paneltop.setBackground(Color.BLACK);
        paneltop.setPreferredSize(new Dimension(100, 30));
        m.f.add(paneltop, BorderLayout.NORTH);
        // layout = = grid layout
        paneltop.setLayout(new GridLayout(1, 10, 0, 3));
        // for horizontal buttons

        // top. pannel buttons and checkbox
        JButton door = new JButton("ADD A DOOR");
        JButton window = new JButton("ADD A WINDOW");

        // Define ComboBox options
        String[] s1 = { "ADD A ROOM", "DragAndDrop" };
        JComboBox<String> room = new JComboBox<>(s1);

        String[] s2 = { "ADD A FURNITURE", "Bed", "Table", "Sofa", "Chair", "diningSet" };
        JComboBox<String> furniture = new JComboBox<>(s2);

        String[] s3 = { "ADD A FIXTURES", "Commode", "Sink", "Shower", "stove", "Washbasin", "Fridge" };
        JComboBox<String> fixtures = new JComboBox<>(s3);

        paneltop.add(room);
        paneltop.add(door);
        paneltop.add(window);
        paneltop.add(furniture);
        paneltop.add(fixtures);

        // DRAWING BORD /
        JPanel panelDB = new JPanel();
        panelDB.setBackground(new Color(255, 220, 230));
        panelDB.setPreferredSize(new Dimension(100, 100));
        m.f.add(panelDB, BorderLayout.CENTER);
        panelDB.setLayout(null);

        //
        JButton save = new JButton("save");
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == save) {

                    m.fs.SafeFile();
                }
            }

        });

        JButton open = new JButton("open");

        open.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                m.fs.loadp(panelDB);
            }

        });

        //

        //
        JButton ClearAll = new JButton("ClearAll");

        ClearAll.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panelDB.removeAll();
                arr.roomsave.clear();
                arr.furnituresave1.clear();
                panelDB.revalidate();
                panelDB.repaint();
            }

        });
        paneltop.add(save);
        paneltop.add(open);
        paneltop.add(ClearAll);

        //

        // Add action listener for the room combo box
        room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // file f = new file();
                String selectedRoom = (String) room.getSelectedItem();
                System.out.println("Selected room: " + selectedRoom);

                if ("DragAndDrop".equals(selectedRoom)) {
                    System.out.println("DragAndDrop option selected for Room");
                    // Add specific action here for "DragAndDrop"

                    // JPanel proom = new JPanel();
                    // proom =
                    // String name = proom.getName();
                    // System.out.println("Room name: " + name);

                    // proom.setVisible(false);
                    if (!arr.checkOverlaps(m.room.rooms(panelDB))) {
                        System.out.println("panel is added to paneldb");
                        // panelDB.add(proom);
                    } else {

                    }

                    print();
                    panelDB.revalidate();
                    panelDB.repaint();
                    panelDB.setVisible(true);

                } else if ("FromPreviousROOM".equals(selectedRoom)) {
                    System.out.println("FromPreviousROOM option selected for Room");
                    // Add specific action here for "FromPreviousROOM"
                }
            }
        });

        door.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JLabel ldoor = new JLabel();
                ldoor = m.o.Door();
                panelDB.add(ldoor);
                System.out.println("Selected door: ");
            }
        });

        window.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel lwindow = new JLabel();
                lwindow = m.o.Window();
                panelDB.add(lwindow);
                System.out.println("Selected window: ");
            }

        });

        // action listeners for funiture combobox
        furniture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFurniture = (String) furniture.getSelectedItem();
                switch (selectedFurniture) {
                    case "Bed":
                        System.out.println("Bed selected");
                        // Add specific action for Bed
                        JLabel lbed = new JLabel();
                        lbed = m.o.Bed();
                        panelDB.add(lbed);
                        break;
                    case "Table":
                        System.out.println("Table selected");
                        // Add specific action for Table
                        JLabel ptable = new JLabel();
                        ptable = m.o.Table();
                        panelDB.add(ptable);

                        break;
                    case "Sofa":
                        System.out.println("Sofa selected");
                        // Add specific action for Sofa
                        JLabel psofa = new JLabel();
                        psofa = m.o.Sofa();
                        panelDB.add(psofa);
                        break;
                    case "diningSet":
                        System.out.println("diningSet selected");
                        // Add specific action for diningSet
                        JLabel pDiningset = new JLabel();
                        pDiningset = m.o.diningSet();
                        panelDB.add(pDiningset);
                        break;
                    case "Chair":
                        System.out.println("Chair selected");
                        // Add specific action for Chair
                        JLabel pchair = new JLabel();
                        pchair = m.o.Chair();
                        panelDB.add(pchair);
                        break;
                    // Add more cases as needed for other furniture items
                    default:
                        System.out.println("No specific furniture selected");
                        break;

                }
            }
        });

        // Add action listener for the fixtures combo box
        fixtures.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFixture = (String) fixtures.getSelectedItem();
                switch (selectedFixture) {
                    case "Commode":
                        System.out.println("Commode selected");
                        JLabel pcommode = new JLabel();
                        pcommode = m.o.Commode();
                        panelDB.add(pcommode);
                        // Add specific action for Commode
                        break;
                    case "Sink":
                        System.out.println("Sink selected");
                        JLabel psink = new JLabel();
                        psink = m.o.Sink();
                        panelDB.add(psink);
                        // Add specific action for Sink
                        break;
                    case "Shower":
                        System.out.println("Shower selected");
                        JLabel pshower = new JLabel();
                        pshower = m.o.Shower();
                        panelDB.add(pshower);
                        // Add specific action for Shower
                        break;
                    case "stove":
                        System.out.println("stove selected");
                        JLabel pstove = new JLabel();
                        pstove = m.o.stove();
                        panelDB.add(pstove);
                        // Add specific action for stove
                        break;
                    case "Washbasin":
                        System.out.println("Washbasin selected");
                        JLabel pwashbasin = new JLabel();
                        pwashbasin = m.o.Washbasin();
                        panelDB.add(pwashbasin);
                        // Add specific action for Washbasin
                        break;
                    case "Fridge":
                        System.out.println("Fridge selected");
                        JLabel pfridge = new JLabel();
                        pfridge = m.o.Fridge();
                        panelDB.add(pfridge);
                        // Add specific action for Fridge
                        break;

                    // Add more cases as needed for other fixtures
                    default:
                        System.out.println("No specific fixture selected");
                        break;
                }
            }
        });

        paneltop.setVisible(true);
        panelDB.setVisible(true);

        paneltop.setVisible(true);

        m.f.setVisible(true);

    }

    public static void print() {
        // file f = new file();
        // String[] s = new String[f.length()];
        // s = f.getnames();
        // System.out.println(s);

    }

}