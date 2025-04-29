package daoImpl;

import java.util.List;

import dao.DAO_TaiKhoan;
import entity.TaiKhoan;
//import gui.FrmChinh;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import utils.HashPassword;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class DAO_TaiKhoan_Impl extends UnicastRemoteObject implements DAO_TaiKhoan {

	private static final long serialVersionUID = 2048486198189875668L;
	private static EntityManager em;
	
	public DAO_TaiKhoan_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer")
				.createEntityManager();
	}

	public boolean xacThucNguoiDung(String tenDangNhapHoacEmail, String matKhau) {
		try {
			// JPQL để tìm tài khoản theo tên đăng nhập hoặc email
			String jpql = "SELECT tk FROM TaiKhoan tk WHERE tk.tenDangNhap = :input OR tk.email = :input";
			TaiKhoan tk = em.createQuery(jpql, TaiKhoan.class)
					.setParameter("input", tenDangNhapHoacEmail)
					.getResultStream()
					.findFirst()
					.orElse(null);

			if (tk == null) {
				return false;
			}

			String hashPasswordInput = HashPassword.hashPassword(matKhau);
			return tk.getMatKhau().equals(hashPasswordInput);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String phanQuyen(String email) {
		String tenDN = "";
	    try {
	        // Thực hiện truy vấn JPQL để lấy tên đăng nhập dựa trên email
	        TypedQuery<String> query = em.createNamedQuery("TaiKhoan.phanQuyen", String.class).setParameter("email", email);
	        
	        tenDN = query.getSingleResult();
	    } catch (NoResultException e) {
	        // Xử lý trường hợp không tìm thấy người dùng
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Không tìm thấy người dùng");
	    } catch (Exception e) {
	        // Xảy ra lỗi khi thực hiện truy vấn
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Lỗi truy vấn");
	    } 
	    return tenDN;
	}

	@Override
	public String getTenNguoiDung(String email) {
		String tenND = "";
	    try {
	        // Thực hiện truy vấn JPQL để lấy tên người dùng dựa trên email
	        TypedQuery<String> query = em.createNamedQuery("TaiKhoan.getTenNguoiDung", String.class).setParameter("email", email);
	        
	        tenND = query.getSingleResult();
	    } catch (NoResultException e) {
	        // Xử lý trường hợp không tìm thấy người dùng
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Không tìm thấy người dùng");
	    } catch (Exception e) {
	        // Xảy ra lỗi khi thực hiện truy vấn
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Lỗi truy vấn");
	    } 
	    return tenND;
	}

	// Hàm lấy mã OTP xong qua bên frm so sánh với ô mã để làm sự kiện
	public int sendEmail(String email){
		final String from = "ttrandanghieu42@gmail.com";
		final String password = "tcth pwux kmfg aokb";


		Properties props = new Properties();
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable","true");

		// create authenticator
		Authenticator auth;
		auth = new Authenticator() {
			@Override
			protected  PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(from,password);
			}       
		};

		Session session = Session.getInstance(props, auth);

		final String to = email;
		MimeMessage msg = new MimeMessage(session);

		try {
			msg.addHeader("Content-type", "text/HTML;charset=UTF-8");
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,false));
			msg.setSubject("OTP quên mật khẩu hiệu sách ONEEIGHT");
			msg.setSentDate(new java.util.Date());
			Random generator = new Random();
			int OTP = 100000 + generator.nextInt(900000);
			msg.setText(String.valueOf(OTP),"UTF-8");

			Transport.send(msg);
			return OTP;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	// Đổi mật khẩu
	@Override
	public boolean doiMatKhau(String email, String newPassword) {
		try {
	        // Tìm người dùng trong cơ sở dữ liệu dựa trên email
	        TypedQuery<TaiKhoan> query = em.createNamedQuery("TaiKhoan.doiMatKhau", TaiKhoan.class).setParameter("email", email);
	        
	        TaiKhoan taiKhoan = query.getSingleResult();
	        
	        // Cập nhật mật khẩu của người dùng thành mật khẩu mới
	        taiKhoan.setMatKhau(newPassword);
	        
	        // Lưu thay đổi vào cơ sở dữ liệu
	        em.merge(taiKhoan);
	        
	        return true; // Trả về true nếu thay đổi thành công
	    } catch (NoResultException e) {
	        // Người dùng không tồn tại
	        e.printStackTrace();
	        return false; // Trả về false nếu không tìm thấy người dùng
	    } catch (Exception e) {
	        // Xảy ra lỗi khi cập nhật mật khẩu
	        e.printStackTrace();
	        return false; // Trả về false nếu có lỗi xảy ra
	    }
	}
	
	@Override
	public List<TaiKhoan> getAllTaiKhoan() {
		return em.createNamedQuery("TaiKhoan.getAllTaiKhoan", TaiKhoan.class).getResultList();
	}

	@Override
	public TaiKhoan getTaiKhoanByEmail(String email) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("TaiKhoan.getTaiKhoanByEmail", TaiKhoan.class).setParameter("email", email).getSingleResult();}



















}
