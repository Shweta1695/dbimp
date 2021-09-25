
package com.example.qr_scan;

import android.graphics.Bitmap;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class StudentModal {

	// variables for our coursename,
	// description, tracks and duration, id.
	private String courseName;
	private String courseDuration;
	private String courseTracks;
	private String courseDescription;
	private int id;
	private Bitmap qrPlaceHolder;

	public Bitmap getQrPlaceHolder(String id) {
		QRGEncoder qrgEncoder = new QRGEncoder(id, null, QRGContents.Type.TEXT, 500);
		Bitmap qrBits = null;
		try {
			qrBits = qrgEncoder.encodeAsBitmap();

		} catch (WriterException e) {
			e.printStackTrace();
		}
		return qrBits;

	}

	public void setQrPlaceHolder(Bitmap qrPlaceHolder) {
		this.qrPlaceHolder = qrPlaceHolder;
	}

	// creating getter and setter methods
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String getCourseTracks() {
		return courseTracks;
	}

	public void setCourseTracks(String courseTracks) {
		this.courseTracks = courseTracks;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StudentModal(String courseName, String courseDuration, String courseTracks, String courseDescription, int id, Bitmap qrPlaceHolder) {
		this.courseName = courseName;
		this.courseDuration = courseDuration;
		this.courseTracks = courseTracks;
		this.courseDescription = courseDescription;
		this.id = id;
		this.qrPlaceHolder = qrPlaceHolder;
	}

	// constructor
	public StudentModal(String courseName, String courseDuration, String courseTracks, String courseDescription) {
		this.courseName = courseName;
		this.courseDuration = courseDuration;
		this.courseTracks = courseTracks;
		this.courseDescription = courseDescription;
	}
}
