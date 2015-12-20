package gontsov;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class FileMenu extends JMenuBar {
    Config conf;
    WorkedPanel workedPanel;

    public FileMenu(Config conf, WorkedPanel workedPanel) {
        this.workedPanel = workedPanel;
        this.conf = conf;
        JMenu menu = new JMenu("file");
        add(menu);

        JMenuItem open = new JMenuItem("open");
        open.addActionListener(new OpenItem());
        menu.add(open);

        JMenuItem save = new JMenuItem("save");
        save.addActionListener(new SaveItem());
        menu.add(save);


    }

    private class SaveItem implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fs = new JFileChooser();
            fs.setDialogTitle("Save a File");
            if (fs.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    ImageIO.write(conf.image, "png", fs.getSelectedFile());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private class OpenItem implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fo = new JFileChooser();
            if (fo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    conf.image = ImageIO.read(fo.getSelectedFile());
                    workedPanel.repaint();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private class FileTypeFilter extends FileFilter {
        String extension, description;

        public FileTypeFilter(String extension, String description) {
            this.extension = extension;
            this.description = description;
        }

        @Override
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            return f.getName().endsWith(extension);
        }

        @Override
        public String getDescription() {
            return description + String.format(" (*%s)", extension);
        }
    }
}
