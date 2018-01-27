package ua.com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "authorId", unique = true, nullable = false)
		private int id;
		
		
		private String firstName;
		private String lastName;
		@JsonIgnore
		@ManyToMany(mappedBy="authors")
		private List<Book> books = new ArrayList<Book>();
		
		public Author() {}
		
		public Author(String firstName, String lastName) {
		
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String authorFirstName) {
			this.firstName = authorFirstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String authorLastName) {
			this.lastName = authorLastName;
		}

		public List<Book> getBooks() {
			return books;
		}

		public void setBooks(List<Book> books) {
			this.books = books;
		}

		@Override
		public String toString() {
			return firstName +" "+ lastName;
		}
}
