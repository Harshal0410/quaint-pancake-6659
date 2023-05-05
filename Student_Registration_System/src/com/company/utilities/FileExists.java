package com.company.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import com.company.entities.Course;
import com.company.entities.Student;

public class FileExists {

	public Map<String, Student> studentFile() {

		Map<String, Student> data = null;
		File f = new File("Students.ser");
		boolean flag = false;

		try {

			if (!f.exists()) {
				f.createNewFile();
				flag = true;
			}

			if (flag) {
				data = new LinkedHashMap<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(data);
				oos.flush();
				return data;
			} else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				data = (Map<String, Student>) ois.readObject();
				return data;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return data;
	}

	public Map<String, Course> courseFile() {

		Map<String, Course> data = null;
		File f = new File("Courses.ser");
		boolean flag = false;

		try {

			if (!f.exists()) {
				f.createNewFile();
				flag = true;
			}

			if (flag) {
				data = new LinkedHashMap<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(data);
				oos.flush();
				return data;
			} else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				data = (Map<String, Course>) ois.readObject();
				return data;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return data;
	}


}
