package gontsov;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
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
        private static final String DELIMITER = ".";

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fs = new JFileChooser();
            fs.setDialogTitle("Save a File");

            String[] extensions = {"png", "bmp", "jpg", "jpeg"};
            for (String extension : extensions) {
                fs.addChoosableFileFilter(new FileTypeFilter(extension));
            }
            fs.setAcceptAllFileFilterUsed(false);

            String extensionActual = fs.getFileFilter().getDescription();
            File fileExpected = null;
            String extensionExpected = null;
            if (fs.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    for (String extension : extensions) {
                        if (extensionActual.equals(extension)) {
                            fileExpected = new File(fs.getSelectedFile().getPath() + DELIMITER + extension);
                            extensionExpected = extension;
                        }
                    }

                    ImageIO.write(conf.image, extensionExpected, fileExpected);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private class FileTypeFilter extends FileFilter {
        public String extension;

        public FileTypeFilter(String extension) {
            this.extension = extension;
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
            return extension;
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
}
