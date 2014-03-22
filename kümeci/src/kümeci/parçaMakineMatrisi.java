package kümeci;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class parçaMakineMatrisi extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					parçaMakineMatrisi frame = new parçaMakineMatrisi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public parçaMakineMatrisi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 976, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 38, 889, 404);
		contentPane.add(scrollPane);
		
		String [] sutunlar = {"bir", "iki","üç"};
		Object [][] veri = {
				{"1","2","3"},
				{"4","5","6"},
		};
		
		
		table = new JTable(veri,sutunlar);
		scrollPane.setViewportView(table);
		
		
		}
		
	}

