import java.sql.*;
import java.sql.DriverManager;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

public class LlamadosDB {
	
	private static String url = "jdbc:sqlite:DBTierraMedia.db";

	public static ResultSet consultaSQL(String sql, Connection conexion) throws SQLException {
		PreparedStatement statement = conexion.prepareStatement(sql);
		return statement.executeQuery();
	}

	public static int escrituraSQL(String sql, Connection conexion) throws SQLException {
		Statement sentencia = conexion.createStatement();
		int salida = sentencia.executeUpdate(sql);
		return salida;
		//PreparedStatement statement = conexion.prepareStatement(sql);
		//return statement.executeUpdate(sql);
	}

	public static List<String> nombresUsuarios(){
		try {
			List<String> salida = new ArrayList<String>();
			Connection conexion = DriverManager.getConnection(url);
	
			ResultSet consulta = consultaSQL("SELECT Nombre FROM Usuarios",conexion);
			while(consulta.next()) {
				salida.add(consulta.getString("Nombre"));
			}
			conexion.close();
			return salida;
		} catch(SQLException err) {
			return null;
		}
	}
	
	public static boolean updateUsuario(String nombre, int oro, double tiempo) {
		try {
			Connection conexion = DriverManager.getConnection(url);
	
			Perfil_Usuario user = selectUsuario(nombre, conexion);
			String query = "UPDATE Usuarios SET Presupuesto='" + (user.getPresupuesto() - oro) + "', TiempoDisponible='"
					+ (user.getHorasDisp() - tiempo) + "' WHERE Nombre='" + nombre + "'";
			int retorno = escrituraSQL(query, conexion);
			if (retorno == 0) {
				throw new SQLException();
			}
			
			
			conexion.close();
			return true;
		} catch (SQLException err) {
			return false;
		}

	}

	public static Perfil_Usuario selectUsuario(String nombre) {
		try {
			Connection conexion = DriverManager.getConnection(url);
	
			String query = "SELECT Preferencia,Presupuesto,TiempoDisponible FROM Usuarios WHERE Nombre='" + nombre
					+ "'";
			ResultSet usr = consultaSQL(query, conexion);
			usr.next();
			Perfil_Usuario user = new Perfil_Usuario(nombre, usr.getString("Preferencia"), usr.getInt("Presupuesto"),
					usr.getDouble("TiempoDisponible"));
			
			conexion.close();
			return user;
		} catch (SQLException err) {
			return null;
		}
	}

	public static Perfil_Usuario selectUsuario(String nombre, Connection conexion) {
		try {
			String query = "SELECT Preferencia,Presupuesto,TiempoDisponible FROM Usuarios WHERE Nombre='" + nombre
					+ "'";
			ResultSet usr = consultaSQL(query, conexion);
			usr.next();
			Perfil_Usuario user = new Perfil_Usuario(nombre, usr.getString("Preferencia"), usr.getInt("Presupuesto"),
					usr.getDouble("TiempoDisponible"));
			return user;
		} catch (SQLException err) {
			return null;
		}
	}

	public static Atraccion selectAtraccion(String nombre, Connection conexion) {
		try {
			String query = "SELECT Atraccion.Nombre,Atraccion.Costo,Atraccion.Tiempo,Atraccion.Cupo,TipoDeAtraccion.Tipo FROM Atraccion INNER JOIN TipoDeAtraccion ON Atraccion.Tipo = TipoDeAtraccion.Id WHERE Nombre='"+nombre+"'";
			ResultSet atrac = consultaSQL(query, conexion);
			atrac.next();
			Atraccion atr = new Atraccion(atrac.getString("Nombre"), atrac.getInt("Costo"), atrac.getDouble("Tiempo"), atrac.getInt("Cupo"), atrac.getString("Tipo"));
			return atr;
		} catch (SQLException err) {
			return null;
		}
	}
	
	public static Atraccion selectAtraccion(String nombre) {
		try {
			Connection conexion = DriverManager.getConnection(url);
	
			String query = "SELECT Atraccion.Nombre,Atraccion.Costo,Atraccion.Tiempo,Atraccion.Cupo,TipoDeAtraccion.Tipo FROM Atraccion INNER JOIN TipoDeAtraccion ON Atraccion.Tipo = TipoDeAtraccion.Id WHERE Nombre LIKE '%"+nombre+"%'";
			ResultSet atrac = consultaSQL(query, conexion);
			atrac.next();
			Atraccion atr = new Atraccion(atrac.getString("Nombre"), atrac.getInt("Costo"), atrac.getDouble("Tiempo"), atrac.getInt("Cupo"), atrac.getString("Tipo"));
			conexion.close();
			return atr;
		} catch (SQLException err) {
			System.out.println(err);
			return null;
		}
	}
	
	public static boolean updateAtraccion(String nombre, Connection conexion) {
		try {
			Atraccion atr = selectAtraccion(nombre,conexion);
			String query = "UPDATE Atraccion SET Cupo='"+(atr.getNCupo()-1)+"' WHERE Nombre='"+nombre+"'";
			int retorno = escrituraSQL(query, conexion);
			if (retorno == 0) {
				throw new SQLException();
			}
			return true;
		} catch (SQLException err) {
			return false;
		}
	}
	
	public static boolean updateAtraccion(String nombre) {
		try {
			Connection conexion = DriverManager.getConnection(url);
	
			Atraccion atr = selectAtraccion(nombre,conexion);
			String query = "UPDATE Atraccion SET Cupo='"+(atr.getNCupo()-1)+"' WHERE Nombre='"+nombre+"'";
			int retorno = escrituraSQL(query, conexion);
			if (retorno == 0) {
				throw new SQLException();
			}
			
			conexion.close();
			return true;
		} catch (SQLException err) {
			System.out.println(err);
			return false;
		}
	}
	
	public static boolean updatePromocion(int id) {
		try {
			Connection conexion = DriverManager.getConnection(url);
	
			Ofertable prom = selectPromocion(id,conexion);
			int tot = prom.getAtracciones().size();
			int res = 0;
			for (Ofertable atr : prom.getAtracciones()) {
				 if(updateAtraccion(atr.getNombre(),conexion)) {
					 res++;
				 }
			}
			
			conexion.close();
			if(res==tot) {
				return true;
			}
			throw new SQLException();
		} catch(SQLException err) {
			return false;
		}
		
	}
	
	public static Ofertable selectPromocion(int id) {
		try {
			Connection conexion = DriverManager.getConnection(url);
	
			String query = "SELECT Promocion.Id, Promocion.Nombre,TipoDeAtraccion.Tipo, Promocion.Atracciones, Promocion.TipoPromocion, Promocion.Especial FROM Promocion INNER JOIN TipoDeAtraccion ON Promocion.Tipo = TipoDeAtraccion.Id WHERE Promocion.Id like '"+id+"'";
			ResultSet prom = consultaSQL(query, conexion);
			Promocion prm = null;
			prom.next();
			switch (prom.getString("TipoPromocion")) {
				case "Porcentuales":
					prm = new PackPorcentuales(prom.getString("Tipo"), prom.getString("Nombre"),
							prom.getString("Atracciones"), Integer.parseInt(prom.getString("Especial")), prom.getInt("Id"));
					break;
				case "Absolutas":
					prm = new PackAbsolutas(prom.getString("Tipo"), prom.getString("Nombre"), prom.getString("Atracciones"),
							Integer.parseInt(prom.getString("Especial")), prom.getInt("Id"));
					break;
				case "AxB":
					prm = new PackAxB(prom.getString("Tipo"), prom.getString("Nombre"), prom.getString("Atracciones"),(prom.getString("Especial")), prom.getInt("Id"));
					break;
			}
			conexion.close();
			return prm;
		} catch (SQLException err) {
			return null;
		}
	}
	
	public static Ofertable selectPromocion(int id, Connection conexion) {

		try {
			String query = "SELECT Promocion.Id ,Promocion.Nombre ,TipoDeAtraccion.Tipo, Promocion.Atracciones, Promocion.TipoPromocion, Promocion.Especial FROM Promocion INNER JOIN TipoDeAtraccion ON Promocion.Tipo = TipoDeAtraccion.Id WHERE Promocion.Id like '"
					+ id + "'";
			ResultSet prom = consultaSQL(query, conexion);
			Promocion prm = null;
			prom.next();
			switch (prom.getString("TipoPromocion")) {
				case "Porcentuales":
					prm = new PackPorcentuales(prom.getString("Tipo"), prom.getString("Nombre"),
							prom.getString("Atracciones"), Integer.parseInt(prom.getString("Especial")), prom.getInt("Id"));
					break;
				case "Absolutas":
					prm = new PackAbsolutas(prom.getString("Tipo"), prom.getString("Nombre"), prom.getString("Atracciones"),
							Integer.parseInt(prom.getString("Especial")), prom.getInt("Id"));
					break;
				case "AxB":
					prm = new PackAxB(prom.getString("Tipo"), prom.getString("Nombre"), prom.getString("Atracciones"),
							prom.getString("Especial"), prom.getInt("Id"));
					break;
			}
			return prm;
		} catch (SQLException err) {
			return null;
		}
	}

	public static boolean insertItinerario(String Nombre,Ofertable compra) {
		try {
			String query = "";
			Connection conexion = DriverManager.getConnection(url);
	
			if(Objects.nonNull(compra.getAtracciones())) {	
				query = "INSERT INTO Itinerarios (Usuario,Promociones,Precio,Tiempo) VALUES ('"+Nombre+"','"+compra.getId()+"','"+compra.getCosto()+"','"+compra.getTiempo()+"')";
			} else {
				query = "INSERT INTO Itinerarios (Usuario,Atracciones,Precio,Tiempo) VALUES ('"+Nombre+"','"+compra.getNombre()+"','"+compra.getCosto()+"','"+compra.getTiempo()+"')";
			}
			int result = escrituraSQL(query,conexion);
			
			conexion.close();
			if(result==0) {
				throw new SQLException();
			}
			return true;
		}
		catch(SQLException err) {
			System.out.println(err);
			return false;
		}
	}

	public static ArrayList<Ofertable> listarOpciones(int presupuesto, Double tiempo, String nombre){
		try {
			boolean pasa = false;
			ArrayList<Ofertable> salida = new ArrayList<Ofertable>();
			List<Ofertable> proms = new ArrayList<Ofertable>();
			Connection conexion = DriverManager.getConnection(url);
			String query = "SELECT Id FROM Promocion";
			ResultSet ids = consultaSQL(query, conexion);
			while(ids.next()) {
				proms.add(selectPromocion(ids.getInt("Id")));
			}
			for(Ofertable promo: proms) {
				if((promo.getCosto()<=presupuesto)&(promo.getTiempo()<=tiempo)) {
					ids = consultaSQL("SELECT Count(Id) as 'cant' FROM Itinerarios WHERE Usuario='"+nombre+"' AND Promociones='"+promo.getId()+"'",conexion);
					ids.next();
					if(ids.getInt("cant")==0) {
						pasa = true;
						for(Atraccion atr : promo.getAtracciones()) {
							ids = consultaSQL("SELECT Count(Id) as 'cant' FROM Itinerarios WHERE Usuario='"+nombre+"' AND Atracciones='"+atr.getNombre()+"'",conexion);
							ids.next();
							if(!(ids.getInt("cant")==0)) {
								pasa = false;
							}
						}
						if(pasa) {
							salida.add(promo);
						}
					}
				}
			}
			ids = consultaSQL("SELECT Nombre FROM Atraccion", conexion);
			proms = new ArrayList<Ofertable>();
			while(ids.next()) {
				proms.add(selectAtraccion(ids.getString("Nombre")));
			}
			for(Ofertable atr: proms) {
				if((atr.getCosto()<=presupuesto)&(atr.getTiempo()<=tiempo)) {
					ids = consultaSQL("SELECT Count(Id) as 'cant' FROM Itinerarios WHERE Usuario='"+nombre+"' AND Atracciones='"+atr.getNombre()+"'",conexion);
					ids.next();
					if(ids.getInt("cant")==0) {
						pasa = true;
						ResultSet tmp = consultaSQL("SELECT Id FROM Promocion WHERE Atracciones LIKE '%"+atr.getNombre()+"%'",conexion);
						while(tmp.next()) {
							Ofertable prom = selectPromocion(tmp.getInt("Id"));
							ResultSet tmp2 = consultaSQL("SELECT Count(Id) as 'cant' FROM Itinerarios Where Usuario='"+nombre+"' AND Promociones='"+prom.getId()+"'",conexion);
							tmp2.next();
							if(!(tmp2.getInt("cant")==0)){
								pasa = false;
							}
						}
						if(pasa) {
							salida.add(atr);
						}
					}
				}
			}
			
			conexion.close();
			return salida;
		} catch(SQLException err) {
			System.out.println(err);
			return null;
		}
	}

}
