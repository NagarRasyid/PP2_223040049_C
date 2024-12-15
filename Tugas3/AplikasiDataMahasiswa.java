package Tugas3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AplikasiDataMahasiswa {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}

class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Aplikasi Data Mahasiswa");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuItemInput = new JMenuItem("Form Input Mahasiswa");
        JMenuItem menuItemExit = new JMenuItem("Keluar");

        menuItemInput.addActionListener(e -> cardLayout.show(mainPanel, "FormPanel"));
        menuItemExit.addActionListener(e -> System.exit(0));

        menu.add(menuItemInput);
        menu.add(menuItemExit);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        mainPanel.add(new FormPanel(), "FormPanel");

        add(mainPanel);
        cardLayout.show(mainPanel, "FormPanel");
    }
}

class FormPanel extends JPanel {
    private JTextField txtNama;
    private JTextArea txtAlamat;
    private JRadioButton rbPria, rbWanita;
    private JCheckBox cbProgramming, cbDesign, cbMarketing;
    private JComboBox<String> comboJurusan;
    private JList<String> listMinat;
    private JSlider sliderUmur;
    private JSpinner spinnerSemester;
    private JTable table;
    private DefaultTableModel tableModel;
    private ButtonGroup genderGroup;

    public FormPanel() {
        setLayout(new BorderLayout());

        JPanel panelInput = new JPanel(new GridLayout(0, 2, 10, 10));
        panelInput.setPreferredSize(new Dimension(600, 400));

        panelInput.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelInput.add(txtNama);

        panelInput.add(new JLabel("Alamat:"));
        txtAlamat = new JTextArea(3, 20);
        txtAlamat.setLineWrap(true);
        txtAlamat.setWrapStyleWord(true);
        panelInput.add(new JScrollPane(txtAlamat));

        panelInput.add(new JLabel("Jenis Kelamin:"));
        JPanel panelGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbPria = new JRadioButton("Pria");
        rbWanita = new JRadioButton("Wanita");
        genderGroup = new ButtonGroup();
        genderGroup.add(rbPria);
        genderGroup.add(rbWanita);
        panelGender.add(rbPria);
        panelGender.add(rbWanita);
        panelInput.add(panelGender);

        panelInput.add(new JLabel("Jurusan:"));
        comboJurusan = new JComboBox<>(new String[] {"Teknik Informatika", "Sistem Informasi", "Desain Komunikasi Visual"});
        panelInput.add(comboJurusan);

        panelInput.add(new JLabel("Minat:"));
        listMinat = new JList<>(new String[] {"Programming", "Design", "Marketing"});
        panelInput.add(new JScrollPane(listMinat));

        panelInput.add(new JLabel("Etos Kerja:"));
        JPanel panelEtosKerja = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cbProgramming = new JCheckBox("Programming");
        cbDesign = new JCheckBox("Design");
        cbMarketing = new JCheckBox("Marketing");
        panelEtosKerja.add(cbProgramming);
        panelEtosKerja.add(cbDesign);
        panelEtosKerja.add(cbMarketing);
        panelInput.add(panelEtosKerja);

        panelInput.add(new JLabel("Umur:"));
        sliderUmur = new JSlider(18, 60);
        sliderUmur.setMajorTickSpacing(10);
        sliderUmur.setMinorTickSpacing(1);
        sliderUmur.setPaintTicks(true);
        sliderUmur.setPaintLabels(true);
        panelInput.add(sliderUmur);

        panelInput.add(new JLabel("Semester:"));
        spinnerSemester = new JSpinner(new SpinnerNumberModel(1, 1, 8, 1));
        panelInput.add(spinnerSemester);

        JScrollPane scrollPaneInput = new JScrollPane(panelInput);
        scrollPaneInput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPaneInput, BorderLayout.NORTH);

        String[] columnNames = {"Nama", "Alamat", "Jenis Kelamin", "Jurusan", "Minat", "Umur", "Semester"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPaneTable = new JScrollPane(table);
        add(scrollPaneTable, BorderLayout.CENTER);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setPreferredSize(new Dimension(100, 30));
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanData();
            }
        });

        JPanel panelButton = new JPanel();
        panelButton.add(btnSimpan);
        add(panelButton, BorderLayout.SOUTH);
    }

    private void simpanData() {
        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();
        String jenisKelamin = rbPria.isSelected() ? "Pria" : rbWanita.isSelected() ? "Wanita" : "";
        String jurusan = (String) comboJurusan.getSelectedItem();
        String minat = listMinat.getSelectedValue();
        int umur = sliderUmur.getValue();
        int semester = (Integer) spinnerSemester.getValue();

        tableModel.addRow(new Object[]{nama, alamat, jenisKelamin, jurusan, minat, umur, semester});

        txtNama.setText("");
        txtAlamat.setText("");
        genderGroup.clearSelection();
        comboJurusan.setSelectedIndex(0);
        listMinat.clearSelection();
        sliderUmur.setValue(18);
        spinnerSemester.setValue(1);
    }
}
