import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainForm extends JFrame {
    private JPanel panelMain;
    private JTextField tfDatum;
    private JTextField tfBenzin;
    private JTextField tfJmeno;
    private JRadioButton dalniceRadioButton;
    private JRadioButton mestoRadioButton;
    private JRadioButton mimoMestoRadioButton;
    private JButton ulozButton;
    private JTextField tfUjeteKilometry;
    private List<JizdaVozidla> seznam = new ArrayList<>();
    private File selectedFile;

    public List<JizdaVozidla> getSeznam() {
        return seznam;
    }

    public MainForm() {
        setTitle("Dodatečný/opravný test: GUI v Javě");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelMain);
        setSize(400, 350);

        initMenu();

        ulozButton.addActionListener(e -> {
            ulozDoSouboru();
        });


    }

    public void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu nastroje = new JMenu("Nástroje");
        menuBar.add(nastroje);

        JMenuItem zobrazSpotrebu = new JMenuItem("Zobraz průměrnou spotřebu");
        nastroje.add(zobrazSpotrebu);
        zobrazSpotrebu.addActionListener(e -> {
            celkovaSpotrebaBenzinu();
        });

        JMenuItem zobrazKilometry = new JMenuItem("Zobraz celkový počet kilometrů ");
        nastroje.add(zobrazKilometry);
        zobrazKilometry.addActionListener(e -> {
            celkovyPocetKilometru();
        });
    }

    public void ulozDoSouboru() {
        JFileChooser fc = new JFileChooser(".");
        int result = fc.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fc.getSelectedFile();
            nactiDoSouboru(selectedFile);
        }
    }

    public void nactiDoSouboru(File selectedFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile, true))) {
            String jmeno = tfJmeno.getText();
            Double benzin = Double.parseDouble(tfBenzin.getText());
            LocalDate datum = LocalDate.parse(tfDatum.getText());
            Integer ujeteKilometry = Integer.parseInt(tfUjeteKilometry.getText());

            int typ;
            if (dalniceRadioButton.isSelected()) {
                typ = 1;
            } else if (mestoRadioButton.isSelected()) {
                typ = 2;
            } else if (mimoMestoRadioButton.isSelected()) {
                typ = 3;
            } else {
                typ = 0;
            }

            JizdaVozidla vozidlo = new JizdaVozidla(datum, benzin, jmeno, ujeteKilometry, typ);
            seznam.add(vozidlo);

            bw.write(datum + ";" + benzin + ";" + jmeno + ";" + ujeteKilometry + ";" + typ + ";");
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void celkovyPocetKilometru() {
        int pocetKilometru = 0;
        for (JizdaVozidla vozidla : seznam) {
            pocetKilometru += vozidla.getUjeteKilometry(); // Přidání ujetých kilometrů z každého záznamu
        }
        JOptionPane.showMessageDialog(this, "Celkový součet ujetých kilometrů: " + pocetKilometru);
    }

    public void celkovaSpotrebaBenzinu() {
        double spotrebaBenzinu = 0.0;
        for (JizdaVozidla vozidla : seznam) {
            spotrebaBenzinu += vozidla.getBenzin(); // Přidání spotřeby benzinu z každého záznamu
        }
        JOptionPane.showMessageDialog(this, "Celková spotřeba benzinu: " + spotrebaBenzinu);
    }

    
}
