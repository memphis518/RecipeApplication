package com.recipewebservice.models;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.Channels;
import java.util.logging.Logger;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.blobstore.BlobKey;
import com.recipewebservice.services.RecipeService;

@PersistenceCapable
public class RecipeImage {

	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String id;
	@Persistent
	private BlobKey blobKey;
	@Persistent
	private Recipe recipe;
	
	@NotPersistent
	private Text image;
	
	private static final Logger log = Logger.getLogger(RecipeService.class.getName());
	
	public RecipeImage() {}
	
	public RecipeImage(Entity entity) {
		this.id       = (String) entity.getProperty("id");
		this.blobKey  = (BlobKey) entity.getProperty("blobKey");
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public BlobKey getBlobKey() {
		return blobKey;
	}
	public void setBlobKey(BlobKey blobKey) {
		this.blobKey = blobKey;
	}
	
	public Text getImage() {
		return image;
	}	
	public void setImage(Text image) {
		this.image = image;
		try{
			this.blobKey = saveImageToBlobStore(image);
		}catch(IOException e ){
			log.severe("Error savimg image: " + e.getLocalizedMessage());
		}
	}
	
	public Recipe getRecipe(){
		return recipe;
	}
	public void setRecipe(Recipe recipe){
		this.recipe = recipe;
	}
	
	private BlobKey saveImageToBlobStore(Text image2) throws IOException{
		FileService fileService = FileServiceFactory.getFileService();
		AppEngineFile file = fileService.createNewBlobFile("application/octet-stream");
		FileWriteChannel writeChannel = fileService.openWriteChannel(file, true);
		PrintWriter out = new PrintWriter(Channels.newWriter(writeChannel, "UTF8"));
		out.println(image2);
		out.close();
		writeChannel.closeFinally();
		BlobKey blobKey = fileService.getBlobKey(file);
		return blobKey;
	}
	
	
	public boolean equals(Object obj) {
		if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    
	    RecipeImage other = (RecipeImage) obj;
	    
	    if( id != other.getId() && 
	    	!id.equals(other.getId())){
	    	return false;
	    }
	    if( blobKey != other.getBlobKey() &&
	    	!blobKey.equals(other.getBlobKey())){
	    	return false;
	    }
	    return true;
	}
	
}
