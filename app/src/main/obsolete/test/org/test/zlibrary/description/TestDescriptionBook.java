package org.test.zlibrary.description;

import junit.framework.TestCase;

import com.laichushu.book.org.geometerplus.fbreader.bookmodel.BookModel;
import com.laichushu.book.org.geometerplus.fbreader.description.Author;
import com.laichushu.book.org.geometerplus.fbreader.description.BookDescription;
import com.laichushu.book.org.geometerplus.zlibrary.core.xml.own.ZLOwnXMLProcessorFactory;
import com.laichushu.book.org.geometerplus.zlibrary.ui.swing.library.ZLSwingLibrary;

public class TestDescriptionBook extends TestCase {
	private final String filename = "testfb2book.fb2";
	private final String filenameZip = "testbookZip.zip";	
	
	public void setUp() {
		new ZLSwingLibrary();
		new ZLOwnXMLProcessorFactory();
	}


	private String myDirectory = "test/data/fb2/filesystem";

	public void testAuthor() {
		BookDescription bd = BookDescription.getDescription(myDirectory+"/"+filename);
		assertTrue(bd != null);
		Author author = bd.getAuthor();
		assertTrue(author != null);
		assertEquals(author.getDisplayName(), "����� ������");
		assertEquals(author.getSortKey(), "������");
		assertEquals(author.isSingle(), true);
	}
	
	public void testLanguageEncoding() {
		BookDescription bd = BookDescription.getDescription(myDirectory+"/"+filename);
		assertTrue(bd != null);
		assertEquals(bd.getEncoding(), "auto");	
		assertEquals(bd.getFileName(), "test/data/fb2/filesystem/testfb2book.fb2");	
		assertEquals(bd.getLanguage(), "ru");	
        //System.out.println(bd.getNumberInSequence());	
		assertEquals(bd.getSequenceName(), "����������� ������ ���������");	
		assertEquals(bd.getTitle(), "�������� ���������");	
	}
	
	public void testGetDescription() {
		BookDescription bd = BookDescription.getDescription(myDirectory+"/"+filename, false);
		assertTrue(bd != null);
		
		BookDescription bd2 = BookDescription.getDescription(myDirectory+"/c"+filename, false);
		assertTrue(bd2 == null);
		
	}
	
	public void testBookModel() {
		BookDescription bd = BookDescription.getDescription(myDirectory+"/"+filename, false);
		assertTrue(bd != null);
		BookModel bm = new BookModel(bd);
		assertTrue(bm != null);
		//System.out.println(bm.getContentsModel().getParagraphsNumber());
	}
	
	

}
