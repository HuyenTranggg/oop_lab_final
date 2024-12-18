package hust.soict.hedspi.aims.screen;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

public class MediaStore extends JPanel{
    private Media media;
    public MediaStore(Media media){
        
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        container.add(new JButton("Add to cart"));
        
        if(media instanceof Playable) {
        	
        	// neu media la playable, them nut Play
        	JButton playButton = new JButton("Play");
            container.add(playButton);
            
            // them su kien cho nut play
            playButton.addActionListener(e -> {
            	
                // Tạo và hiển thị JDialog
                JDialog playDialog = new JDialog();
                playDialog.setTitle("Playing Media");
                playDialog.setSize(300, 200);
                playDialog.setLocationRelativeTo(null); // Căn giữa màn hình

                // Hiển thị thông báo
                JLabel message = new JLabel("Playing: " + media.getTitle());
                message.setHorizontalAlignment(JLabel.CENTER);
                playDialog.add(message);

                // Gọi phương thức play() từ Playable
                String playInfo = media.play();
                
                // Hiển thị thông tin trong JTextArea (cho phép hiển thị nhiều dòng)
                JTextArea textArea = new JTextArea(playInfo);
                textArea.setEditable(false);
                textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                JScrollPane scrollPane = new JScrollPane(textArea); // Cho phép cuộn nếu nội dung dài

                // Thêm JTextArea vào JDialog
                playDialog.add(scrollPane);
                
                // Hiển thị JDialog
                playDialog.setVisible(true);
            });
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    
}

