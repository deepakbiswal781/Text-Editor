import java.awt.print.PrinterException;
import  java.io.*;
import  java.awt.*;
import  java.awt.event.*;
import  javax.swing.*;
import javax.swing.text.*;
import  javax.swing.plaf.metal.*;
class Editor extends JFrame implements ActionListener {
    JFrame f;
    JTextArea t;
    JMenuBar menu;
    JMenu file, edit;
    JMenuItem f1, f2, f3, f4, e1, e2, e3, close;

    Editor() {
        f = new JFrame("Tenam's NotePaper");
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {
            System.out.println("Error setting look and feel " + e);
        }
        t = new JTextArea();
        menu = new JMenuBar();
        file = new JMenu("File");
        f1 = new JMenuItem("New");
        f2 = new JMenuItem("Open");
        f3 = new JMenuItem("Save");
        f4 = new JMenuItem("Print");

        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);

        edit = new JMenu("Edit");
        e1 = new JMenuItem("Cut");
        e2 = new JMenuItem("Copy");
        e3 = new JMenuItem("Paste");

        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);

        edit.add(e1);
        edit.add(e2);
        edit.add(e3);
        close = new JMenuItem("Close");
        close.addActionListener(this);
        menu.add(file);
        menu.add(edit);
        menu.add(close);

        f.setJMenuBar(menu);
        f.add(t);
        f.setSize(500, 500);
        f.show();
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("New")) {
            t.setText("");
        } else if (s.equals("Open")) {

            JFileChooser j=new JFileChooser("E: ");

            int r=j.showOpenDialog(null);

            if(r==JFileChooser.APPROVE_OPTION){
                File fi=new File(j.getSelectedFile().getAbsolutePath());
            try {
                String s1 = "", s2 = "";

                FileReader fr = new FileReader(fi);

                BufferedReader br = new BufferedReader(fr);

                s2 = br.readLine();

                while ((s1 = br.readLine()) != null) {
                    s2 = s2 + '\n' + s1;
                }
                t.setText(s2);
            }catch(Exception et){
                JOptionPane.showMessageDialog(f,et.getMessage());
            }

            }
        } else if (s.equals("Save")) {
                JFileChooser j=new JFileChooser("E: ");
                int r=j.showSaveDialog(null);
                if(r == JFileChooser.APPROVE_OPTION){
                    File fi=new File(j.getSelectedFile().getAbsolutePath());

                    try{
                        FileWriter fw=new FileWriter(fi,false);
                        BufferedWriter bw=new BufferedWriter(fw);
                        bw.write(t.getText());
                        bw.flush();
                        bw.close();
                    }
                    catch(Exception et){
                        JOptionPane.showMessageDialog(f,et.getMessage());
                    }
                }
        } else if (s.equals("Print")) {
            try{
                t.print();
            }catch(PrinterException ex){
                throw new RuntimeException(ex);
            }
        } else if (s.equals("Cut")) {
            t.cut();
        }else if(s.equals("Copy")){
            t.copy();
        }else if(s.equals("Paste")){
            t.paste();
        }else if(s.equals("Close")){
            f.setVisible(false);
        }
    }

    public static void main(String[] args)throws Exception {
        Editor e=new Editor();
    }
}