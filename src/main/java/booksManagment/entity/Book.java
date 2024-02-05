package booksManagment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@NotNull(message = "*Please enter book title")
	@NotBlank(message = "*Please enter book title")
	@Length(min = 3, message = "*Min 3 characters.")

	@Column(name = "title")
	private String title;

	@NotNull(message = "*Please enter book authors")
	@NotBlank(message = "*Please enter book authors")
	@Length(min = 4, message = "*Min 4 characters.")
	@Length(max = 60, message = "*Max 60 characters.")
	@Column(name = "authors")
	private String author;
	@NotNull(message = "*Please enter price")
	@Min(value = 5, message = "*Minimal price is 5.00 PLN")
	@Column(name = "price")
	private Double price;

	// @Lob
	// private byte[] coverPhoto;

	public Book(Long id, String title,
			// byte[] coverPhoto,
			String author, Double price) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		// this.coverPhoto = coverPhoto;
	}

	public Book() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	// public byte[] getCoverPhoto() {
	// return coverPhoto;
	// }

	// public void setCoverPhoto(byte[] coverPhoto) {
	// this.coverPhoto = coverPhoto;
	// }

}
