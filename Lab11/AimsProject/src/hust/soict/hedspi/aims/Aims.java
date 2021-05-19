package hust.soict.hedspi.aims;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;

import javax.naming.LimitExceededException;
import javax.swing.*;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.book.Book;
import hust.soict.hedspi.aims.media.disc.CompactDisc;
import hust.soict.hedspi.aims.media.disc.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.disc.Disc;
import hust.soict.hedspi.aims.media.disc.track.Track;
import hust.soict.hedspi.aims.order.Order;

public class Aims extends JFrame {
    private JPanel displayPanel;
    private JButton createOrderBtn = new JButton("New order");
    private JButton addItemBtn = new JButton("Add");
    private JTextField termField = new JTextField();
    private JButton searchItemBtn = new JButton("Search");
    private JButton deleteItemBtn = new JButton("Delete");
    private JButton totalCostBtn = new JButton("Place order");
    private JButton playBtn = new JButton("Play");
    private JScrollPane scrollPane;
    private JPanel playAndTotaPanel;
    private JPanel playPanel;
    private JPanel totalPanel;
    private JList<Media> mediaList;
    private Order order;
    private int mediaCount;

    public Aims() {
        // get this frame
        JFrame f = this;
        // containter
        Container parentContainer = getContentPane();
        parentContainer.setLayout(new BorderLayout()); // The content-pane sets its layout

        // header for create order button
        parentContainer.add(new JPanel() {
            {
                setLayout(new FlowLayout());
                add(createOrderBtn);
            }
        }, BorderLayout.NORTH);

        // footer for another button
        termField.setColumns(10);
        parentContainer.add(new JPanel() {
            {
                setLayout(new FlowLayout());
                add(playBtn);
                add(addItemBtn);
                add(termField);
                add(searchItemBtn);
                add(deleteItemBtn);
                add(totalCostBtn);
            }
        }, BorderLayout.SOUTH);

        try {
            order = Order.createOrder();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentContainer, e.getMessage(), "Something went wrong!",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }

        // test media
        mediaCount += 3;
        DigitalVideoDisc dvd = new DigitalVideoDisc(1, "Abcddd", "def", "gth", 30, 20f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Abc2", "def", "gth", 30, 20f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc(3, "Abc3", "def", "gth", 30, 20f);
        order.addMedia(dvd, dvd2, dvd3);

        // display media
        displayPanel = new JPanel(new BorderLayout());
        displayPanel.add(new JLabel("Order " + order.getId(), SwingConstants.CENTER), BorderLayout.NORTH);
        mediaList = new JList<Media>(new Vector<Media>(order.getItemOrdered()));
        //mediaList.setFont(new Font("monospaced", Font.PLAIN, 15));
        scrollPane = new JScrollPane(mediaList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        displayPanel.add(scrollPane, BorderLayout.CENTER);
        playAndTotaPanel = new JPanel(new GridLayout(1, 2));
        playPanel = new JPanel(new FlowLayout());
        totalPanel = new JPanel(new FlowLayout());
        playAndTotaPanel.add(playPanel);
        playAndTotaPanel.add(totalPanel);
        displayPanel.add(playAndTotaPanel, BorderLayout.SOUTH);
        parentContainer.add(displayPanel);

        // create order event
        createOrderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    order = Order.createOrder();
                    displayPanel.removeAll();
                    displayPanel.add(new JLabel("Order " + order.getId(), SwingConstants.CENTER), BorderLayout.NORTH);
                    mediaList = new JList<Media>(new Vector<Media>(order.getItemOrdered()));
                    //mediaList.setFont(new Font("monospaced", Font.PLAIN, 15));
                    scrollPane = new JScrollPane(mediaList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    displayPanel.add(scrollPane, BorderLayout.CENTER);
                    playAndTotaPanel = new JPanel(new GridLayout(1, 2));
                    playPanel = new JPanel(new FlowLayout());
                    totalPanel = new JPanel(new FlowLayout());
                    playAndTotaPanel.add(playPanel);
                    playAndTotaPanel.add(totalPanel);
                    displayPanel.add(playAndTotaPanel, BorderLayout.SOUTH);
                    displayPanel.revalidate();
                    displayPanel.repaint();
                } catch (LimitExceededException e) {
                    JOptionPane.showMessageDialog(parentContainer, e.getMessage(), "Limit's reached",
                            JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(parentContainer, e.getMessage(), "Something went wrong!",
                            JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        });

        // add event
        addItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // popup dialog
                JDialog inputForm = new JDialog(f, "Add Media", true);
                inputForm.setLayout(new FlowLayout());
                // select media type
                JButton confirmBtn = new JButton("OK");
                String[] options = { "CD", "DVD", "Book" };
                JComboBox<String> chooseMediaBox = new JComboBox<String>(options);
                inputForm.add(chooseMediaBox);
                inputForm.add(confirmBtn);
                confirmBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        String choosed = (String) chooseMediaBox.getSelectedItem();
                        System.out.println(choosed);
                        inputForm.dispose();

                        if (choosed.equals("DVD")) { // DVD choosed
                            JDialog inputDVDForm = new JDialog(f, "Add DVD", true);
                            inputDVDForm.setLayout(new GridLayout(6, 1));
                            JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                            JLabel titleLabel = new JLabel("Title:");
                            JTextField titleField = new JTextField();
                            titleField.setColumns(25);
                            titlePanel.add(titleLabel);
                            titlePanel.add(titleField);
                            JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                            JLabel categoryLabel = new JLabel("Category:");
                            JTextField categoryField = new JTextField();
                            categoryField.setColumns(25);
                            categoryPanel.add(categoryLabel);
                            categoryPanel.add(categoryField);
                            JPanel directorPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                            JLabel directorLabel = new JLabel("Director:");
                            JTextField directorField = new JTextField();
                            directorField.setColumns(25);
                            directorPanel.add(directorLabel);
                            directorPanel.add(directorField);
                            JPanel lengthPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                            JLabel lengthLabel = new JLabel("Length:");
                            JTextField lengthField = new JTextField();
                            lengthField.setColumns(25);
                            lengthPanel.add(lengthLabel);
                            lengthPanel.add(lengthField);
                            JPanel costPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                            JLabel costLabel = new JLabel("Cost($):");
                            JTextField costField = new JTextField();
                            costField.setColumns(25);
                            costPanel.add(costLabel);
                            costPanel.add(costField);
                            JPanel btnPanel = new JPanel(new FlowLayout());
                            JButton addConfirmBtn = new JButton("Add");
                            btnPanel.add(addConfirmBtn);
                            inputDVDForm.add(titlePanel);
                            inputDVDForm.add(categoryPanel);
                            inputDVDForm.add(directorPanel);
                            inputDVDForm.add(lengthPanel);
                            inputDVDForm.add(costPanel);
                            inputDVDForm.add(btnPanel);

                            addConfirmBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    if (titleField.getText().isEmpty() || categoryField.getText().isEmpty()
                                            || directorField.getText().isEmpty() || lengthField.getText().isEmpty()
                                            || costField.getText().isEmpty()) {
                                        JOptionPane.showMessageDialog(inputDVDForm, "Must fill all text fields",
                                                "Warning", JOptionPane.WARNING_MESSAGE);
                                        return;
                                    }
                                    int length;
                                    try {
                                        length = Integer.valueOf(lengthField.getText());
                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(inputDVDForm,
                                                "Length field must be integer number!", "Warning",
                                                JOptionPane.WARNING_MESSAGE);
                                        return;
                                    }
                                    float cost;
                                    try {
                                        cost = Float.valueOf(costField.getText());
                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(inputDVDForm, "Cost field must be real number!",
                                                "Warning", JOptionPane.WARNING_MESSAGE);
                                        return;
                                    }
                                    if (length < 0 || cost < 0) {
                                        JOptionPane.showMessageDialog(inputDVDForm, "Length and cost must not be negative!",
                                                "Warning", JOptionPane.WARNING_MESSAGE);
                                        return;
                                    }
                                    mediaCount++;
                                    order.addMedia(new DigitalVideoDisc(mediaCount, titleField.getText(),
                                            categoryField.getText(), directorField.getText(), length, cost));
                                    inputDVDForm.dispose();
                                    mediaList.removeAll();
                                    mediaList.setListData(new Vector<Media>(order.search(termField.getText())));
                                    displayPanel.revalidate();
                                    displayPanel.repaint();
                                }
                            });
                            inputDVDForm.setSize(400, 300);
                            inputDVDForm.setResizable(false);
                            inputDVDForm.setVisible(true);

                        } else if (choosed.equals("CD")) { // CD choosed
                            JDialog inputCDForm = new JDialog(f, "Add CD", true);
                            inputCDForm.setLayout(new GridLayout(0, 1));
                            JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                            JLabel titleLabel = new JLabel("Title:");
                            JTextField titleField = new JTextField();
                            titleField.setColumns(25);
                            titlePanel.add(titleLabel);
                            titlePanel.add(titleField);
                            JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                            JLabel categoryLabel = new JLabel("Category:");
                            JTextField categoryField = new JTextField();
                            categoryField.setColumns(25);
                            categoryPanel.add(categoryLabel);
                            categoryPanel.add(categoryField);
                            JPanel artistPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                            JLabel artistLabel = new JLabel("Artist:");
                            JTextField artistField = new JTextField();
                            artistField.setColumns(25);
                            artistPanel.add(artistLabel);
                            artistPanel.add(artistField);
                            JPanel costPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                            JLabel costLabel = new JLabel("Cost($):");
                            JTextField costField = new JTextField();
                            costField.setColumns(25);
                            costPanel.add(costLabel);
                            costPanel.add(costField);
                            JPanel btnPanel = new JPanel(new FlowLayout());
                            JButton addConfirmBtn = new JButton("Add");
                            JButton addTrackBtn = new JButton("Add Track");
                            btnPanel.add(addTrackBtn);
                            btnPanel.add(addConfirmBtn);
                            inputCDForm.add(titlePanel);
                            inputCDForm.add(categoryPanel);
                            inputCDForm.add(artistPanel);
                            inputCDForm.add(costPanel);
                            inputCDForm.add(btnPanel);
                            JList<Track> trackList = new JList<Track>(new Vector<Track>());
                            JScrollPane scrollTrackPane = new JScrollPane(trackList,
                                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                            inputCDForm.add(scrollTrackPane);
                            List<Track> tracks = new ArrayList<Track>();
                            addTrackBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    JDialog inputTrackForm = new JDialog(f, "Add Track", true);
                                    inputTrackForm.setLayout(new GridLayout(0, 1));
                                    JPanel titleTrackPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                                    JLabel titleTrackLabel = new JLabel("Title:");
                                    JTextField titleTrackField = new JTextField();
                                    titleTrackField.setColumns(25);
                                    titleTrackPanel.add(titleTrackLabel);
                                    titleTrackPanel.add(titleTrackField);
                                    JPanel lengthTrackPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                                    JLabel lengthTrackLabel = new JLabel("Length:");
                                    JTextField lengthTrackField = new JTextField();
                                    lengthTrackField.setColumns(25);
                                    lengthTrackPanel.add(lengthTrackLabel);
                                    lengthTrackPanel.add(lengthTrackField);
                                    JPanel btnPanel = new JPanel(new FlowLayout());
                                    JButton addConfirmBtn = new JButton("Add");
                                    btnPanel.add(addConfirmBtn);
                                    inputTrackForm.add(titleTrackPanel);
                                    inputTrackForm.add(lengthTrackPanel);
                                    inputTrackForm.add(btnPanel);
                                    addConfirmBtn.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent evt) {
                                            if (titleTrackField.getText().isEmpty()
                                                    || lengthTrackField.getText().isEmpty()) {
                                                JOptionPane.showMessageDialog(inputTrackForm,
                                                        "Must fill all text fields", "Warning",
                                                        JOptionPane.WARNING_MESSAGE);
                                                return;
                                            }
                                            int length;
                                            try {
                                                length = Integer.valueOf(lengthTrackField.getText());
                                            } catch (Exception e) {
                                                JOptionPane.showMessageDialog(inputTrackForm,
                                                        "Length field must be integer number!", "Warning",
                                                        JOptionPane.WARNING_MESSAGE);
                                                return;
                                            }
                                            if (length < 0) {
                                                JOptionPane.showMessageDialog(inputTrackForm, "Length must not be negative!",
                                                        "Warning", JOptionPane.WARNING_MESSAGE);
                                                return;
                                            }
                                            inputTrackForm.dispose();
                                            trackList.removeAll();
                                            tracks.add(new Track(titleTrackField.getText(), length));
                                            trackList.setListData(new Vector<Track>(tracks));
                                            trackList.revalidate();
                                            trackList.repaint();
                                        }
                                    });
                                    inputTrackForm.setSize(400, 200);
                                    inputTrackForm.setResizable(false);
                                    inputTrackForm.setVisible(true);
                                }
                            });

                            addConfirmBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    if (titleField.getText().isEmpty() || categoryField.getText().isEmpty()
                                            || artistField.getText().isEmpty() || costField.getText().isEmpty()) {
                                        JOptionPane.showMessageDialog(inputCDForm, "Must fill all text fields",
                                                "Warning", JOptionPane.WARNING_MESSAGE);
                                        return;
                                    }
                                    float cost;
                                    try {
                                        cost = Float.valueOf(costField.getText());
                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(inputCDForm, "Cost field must be real number!",
                                                "Warning", JOptionPane.WARNING_MESSAGE);
                                        return;
                                    }
                                    if (cost < 0) {
                                        JOptionPane.showMessageDialog(inputCDForm, "Cost must not be negative!",
                                                "Warning", JOptionPane.WARNING_MESSAGE);
                                        return;
                                    }
                                    mediaCount++;
                                    order.addMedia(new CompactDisc(mediaCount, titleField.getText(),
                                            categoryField.getText(), cost, artistField.getText(), tracks));
                                    inputCDForm.dispose();
                                    mediaList.removeAll();
                                    mediaList.setListData(new Vector<Media>(order.search(termField.getText())));
                                    displayPanel.revalidate();
                                    displayPanel.repaint();
                                }
                            });
                            inputCDForm.setSize(400, 300);
                            inputCDForm.setResizable(false);
                            inputCDForm.setVisible(true);
                        } else { // Book choosed
                            JDialog inputBookForm = new JDialog(f, "Add Book", true);
                            inputBookForm.setLayout(new GridLayout(5, 1));
                            JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                            JLabel titleLabel = new JLabel("Title:");
                            JTextField titleField = new JTextField();
                            titleField.setColumns(25);
                            titlePanel.add(titleLabel);
                            titlePanel.add(titleField);
                            JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                            JLabel categoryLabel = new JLabel("Category:");
                            JTextField categoryField = new JTextField();
                            categoryField.setColumns(25);
                            categoryPanel.add(categoryLabel);
                            categoryPanel.add(categoryField);
                            JPanel artistsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                            JLabel artistsLabel = new JLabel("Artists:");
                            JTextField artistsField = new JTextField();
                            artistsField.setColumns(25);
                            artistsPanel.add(artistsLabel);
                            artistsPanel.add(artistsField);
                            JPanel costPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                            JLabel costLabel = new JLabel("Cost($):");
                            JTextField costField = new JTextField();
                            costField.setColumns(25);
                            costPanel.add(costLabel);
                            costPanel.add(costField);
                            JPanel btnPanel = new JPanel(new FlowLayout());
                            JButton addConfirmBtn = new JButton("Add");
                            btnPanel.add(addConfirmBtn);
                            inputBookForm.add(titlePanel);
                            inputBookForm.add(categoryPanel);
                            inputBookForm.add(artistsPanel);
                            inputBookForm.add(costPanel);
                            inputBookForm.add(btnPanel);

                            addConfirmBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    if (titleField.getText().isEmpty() || categoryField.getText().isEmpty()
                                            || costField.getText().isEmpty()) {
                                        JOptionPane.showMessageDialog(inputBookForm,
                                                "Must fill all text fields, except artists", "Warning",
                                                JOptionPane.WARNING_MESSAGE);
                                        return;
                                    }
                                    float cost;
                                    try {
                                        cost = Float.valueOf(costField.getText());
                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(inputBookForm, "Cost field must be real number!",
                                                "Warning", JOptionPane.WARNING_MESSAGE);
                                        return;
                                    }
                                    if (cost < 0) {
                                        JOptionPane.showMessageDialog(inputBookForm, "Cost must not be negative!",
                                                "Warning", JOptionPane.WARNING_MESSAGE);
                                        return;
                                    }
                                    List<String> artists = new ArrayList<String>();
                                    if (!artistsField.getText().isEmpty()) {
                                        String[] artistsStr = artistsField.getText().split(";\\s+");
                                        artists = Arrays.asList(artistsStr);
                                    }
                                    mediaCount++;
                                    order.addMedia(new Book(mediaCount, titleField.getText(), categoryField.getText(),
                                            cost, artists));
                                    inputBookForm.dispose();
                                    mediaList.removeAll();
                                    mediaList.setListData(new Vector<Media>(order.search(termField.getText())));
                                    displayPanel.revalidate();
                                    displayPanel.repaint();
                                }
                            });
                            inputBookForm.setSize(400, 300);
                            inputBookForm.setResizable(false);
                            inputBookForm.setVisible(true);
                        }
                    }
                });

                inputForm.setSize(400, 100);
                inputForm.setResizable(false);
                inputForm.setVisible(true);
            }
        });

        // search event
        searchItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // if field is null, display all media
                if (termField.getText() == null) {
                    mediaList.removeAll();
                    mediaList.setListData(new Vector<Media>(order.getItemOrdered()));
                    displayPanel.revalidate();
                    displayPanel.repaint();
                    return;
                }
                // search and display media
                mediaList.removeAll();
                mediaList.setListData(new Vector<Media>(order.search(termField.getText())));
                displayPanel.revalidate();
                displayPanel.repaint();
            }
        });

        // delete event
        deleteItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // check if not select any media
                if (mediaList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(parentContainer, "Select media first", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // delete selected medias
                for (Media media : mediaList.getSelectedValuesList()) {
                    order.removeMedia(media);
                }
                // display new medias
                mediaList.removeAll();
                mediaList.setListData(new Vector<Media>(order.getItemOrdered()));
                displayPanel.revalidate();
                displayPanel.repaint();
            }
        });

        // play event
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (mediaList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(parentContainer, "Select playable media first", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (mediaList.getSelectedValue() instanceof Book) {
                    JOptionPane.showMessageDialog(parentContainer, "Can't play book?", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String onplay = "On playing Media: " + mediaList.getSelectedValue().getId();
                try {
                    ((Disc) mediaList.getSelectedValue()).play();
                } catch (PlayerException e) {
                    JOptionPane.showMessageDialog(parentContainer, "This media has no length!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                    return;
                }
                JLabel annouLabel = new JLabel(onplay);
                playPanel.removeAll();
                playPanel.add(annouLabel);
                playPanel.revalidate();
                playPanel.repaint();
            }
        });

        // total button
        totalCostBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JLabel luckyLabel = new JLabel();
                JLabel totalLabel = new JLabel();
                JDialog luckyItemDialog = new JDialog(f, "Place order", true);
                JLabel titleLabel = new JLabel("Lucky Item", SwingConstants.CENTER);
                luckyItemDialog.setLayout(new BorderLayout());
                JTextArea details = new JTextArea("Lucky Item detail:\nYou need an order of 322$ with 7 items or more to get a Lucky Item\nThe Lucky Item is totally FREE!\nThe higher the total price of an order is, the higher the value of Lucky Item can be\nThe price of Lucky Item is not greater than 25% total cost.\nLucky rate: 80%");
                details.setEditable(false);
                details.setLineWrap(true);
                details.setWrapStyleWord(true);
                JPanel btnPanel = new JPanel(new FlowLayout());
                JButton processBtn = new JButton("Process");
                processBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        totalPanel.removeAll();
                        Media media = order.getALuckyItem(322, 7, 0.8f, 0.25f);
                        if (media == null) {
                            JOptionPane.showMessageDialog(luckyItemDialog, "You didn't get Lucky Item. :(", "So bad!",
                            JOptionPane.INFORMATION_MESSAGE);
                        }
                        else {
                            String luckyStr = "You got a Lucky Item :). The Lucky Item is " + media.getId();
                            JOptionPane.showMessageDialog(luckyItemDialog, luckyStr, "Great!",
                            JOptionPane.INFORMATION_MESSAGE);
                            luckyLabel.setText("The Lucky Item is " + media.getId() + ".");
                            totalPanel.add(luckyLabel);
                        }
                        luckyItemDialog.dispose();
                        totalLabel.setText("Total cost: " + order.totalCost() + "$");
                        totalPanel.add(totalLabel);
                        totalPanel.revalidate();
                        totalPanel.repaint();
                    }
                });
                luckyItemDialog.add(titleLabel, BorderLayout.NORTH);
                luckyItemDialog.add(details, BorderLayout.CENTER);
                btnPanel.add(processBtn);
                luckyItemDialog.add(btnPanel, BorderLayout.SOUTH);
                luckyItemDialog.setSize(400, 300);
                luckyItemDialog.setResizable(false);
                luckyItemDialog.setVisible(true);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit program if close-window button clicked
        setTitle("Aims Project"); // "super" JFrame sets title
        setSize(800, 600); // "super" JFrame sets initial size
        setMinimumSize(new Dimension(400, 300));
        setVisible(true); // "super" JFrame shows
    }

    public static void main(String[] args) {
        // Run the GUI construction in the Event-Dispatching thread for thread-safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Aims(); // Let the constructor do the job
            }
        });
    }
}
