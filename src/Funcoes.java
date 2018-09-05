import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;

public class Funcoes {
	
	public static void delete(File file) {
		try {
			file.delete();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void status(File file) {
		try {
			if(file.isFile()) {
				Date d = new Date(file.lastModified());
				System.out.println("Essas são as informações do seu arquivo:");
				System.out.println("~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Local: "+ file.getName());
				System.out.println("Tamanho utilizado: "+ (file.length())/1024+"KB");
				System.out.println("Última modificação em: "+ d);
				if(file.isHidden()) {
					System.out.println("É um arquivo oculto");
				}else {
					System.out.println("Não é um arquivo oculto");
				}
				
			} else {
				System.out.println("Não é possivel mostrar informações de diretórios nessa atualização");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void listarRoot() {
		   try {
			   File[] roots;
			   roots = File.listRoots();
		       for(File path:roots) {
		       System.out.println(path);
		   }  		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void listarDir(File diretorio) {
		   try { 
		        File[] pastas;
		        
		        pastas = diretorio.listFiles();
		         
		        for(File path:pastas) {
		        	 System.out.println(path);
		         }
		         
		      } catch(Exception e) {
		         e.printStackTrace();
		      }
	}
	
	public static void copiarArquivo(File in) {
		try{
			if(in.isDirectory() ==  true && in.isFile() == false) {
				System.out.println("Pastas não popdem ser copiadas nessa versão");
				return;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		
		byte[] buffer = new byte[1024];
		
		File out = new File(in.getParentFile()+ "\\copia"+ in.getName());
		
		
		//buffer.
		
		try {
			
			InputStream is = new FileInputStream(in);
			//DataInputStream dis = new DataInputStream(is);
			
			OutputStream os = new FileOutputStream(out);
			//DataOutputStream dos = new DataOutputStream(os);
			
			//dis.read(buffer);
			//dos.write(buffer);
			
			while((is.read(buffer))> 0) {
				os.write(buffer);
			}
			
			//dis.close();
			is.close();
			//dos.close();
			os.close();
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public static void search(String termo,File diretorio) {
		   try {
			   if (diretorio.canRead()) {
				/*   FileFilter filtro = new FileFilter() {
				    	public boolean accept(File pathname) {
				    		return (pathname.getName().toString().equals(termo));
				    	}
				      };
				  */ 
				   for(File dir : diretorio.listFiles()) {
					if(dir.getName().toString().toLowerCase().contains(termo.toLowerCase())) {
						if(dir.isDirectory()){
			        		System.out.println("Foi encontrado um diretório em "+dir);
			        	}else if(dir.isFile()) {
			        		System.out.println("Foi encontrado um arquivo em "+dir);
			        	}
					}
					if(dir.isDirectory()){
						search(termo,dir);
			        	}
			   }
			   }   
		   } catch(Exception e) {
			   
			   }
	}
	
	public static void listaTudo() {
		   for(File root : File.listRoots()) {
	      	 listarSubDir(root);
	       }
	}
	
	
	public static void listarSubDir(File diretorio) {
		   try {
			   if (diretorio.canRead()) {
				   for(File nome : diretorio.listFiles()) {
					System.out.println(nome);
					if(nome.isDirectory()){
						listarSubDir(nome);
			        	}
					}
			   }
		   } catch(Exception e) {

			   }
	}

}
