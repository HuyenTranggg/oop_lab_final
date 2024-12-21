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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

public class MediaStore extends JPanel{
    private Media media;
    public MediaStore(Media media, Cart cart){
        
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        // them nut Add To Cart
        JButton addToCartButton = new JButton("Add to cart");
        container.add(addToCartButton);
        
        // them su kien cho nut add to cart
        addToCartButton.addActionListener(e -> {
            cart.addMedia(media); // Thêm media vào giỏ hàng
        });
        
        if(media instanceof Playable) {
        	
        	// neu media la playable, them nut Play
        	JButton playButton = new JButton("Play");
            container.add(playButton);
            
            // them su kien cho nut play
            playButton.addActionListener(e -> {
                try {
                    // Gọi phương thức play() từ Playable
                    Playable playableMedia = (Playable) media; // Ép kiểu sang Playable
                    String playInfo = playableMedia.play(); // Gọi play()

                    // Tạo và hiển thị JDialog
                    JDialog playDialog = new JDialog();
                    playDialog.setTitle("Playing Media");
                    playDialog.setSize(400, 300);
                    playDialog.setLocationRelativeTo(null); // Căn giữa màn hình

                    // Hiển thị thông tin trong JTextArea
                    JTextArea textArea = new JTextArea(playInfo);
                    textArea.setEditable(false);
                    textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                    JScrollPane scrollPane = new JScrollPane(textArea); // Cho phép cuộn nếu nội dung dài

                    playDialog.add(scrollPane); // Thêm JTextArea vào JDialog
                    playDialog.setVisible(true); // Hiển thị JDialog
                } catch (Exception ex) {
                    // Hiển thị thông báo lỗi nếu phát sinh lỗi khi play()
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Play Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
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

