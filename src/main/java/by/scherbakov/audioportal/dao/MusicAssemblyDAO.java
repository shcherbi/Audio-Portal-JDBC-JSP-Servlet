package by.scherbakov.audioportal.dao;

import by.scherbakov.audioportal.database.ConnectionPool;
import by.scherbakov.audioportal.entity.MusicAssembly;
import by.scherbakov.audioportal.exception.CommonException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class {@code MusicAssemblyDAO} is used to connect with data base.
 * Does all actions related with music assemblies.
 *
 * @author ScherbakovIlia
 * @see AbstractDAO
 */

public class MusicAssemblyDAO implements AbstractDAO<MusicAssembly> {
    public static final Logger LOGGER = LogManager.getLogger(MusicAssemblyDAO.class);

    private static final String ASSEMBLY_ID = "idAssembly";
    private static final String NAME = "name";

    private static final String SQL_SELECT_ASSEMBLY = "SELECT idAssembly, `assembly`.name FROM assembly";
    private static final String SQL_FIND_ASSEMBLY_BY_ID = "SELECT idAssembly, `assembly`.name FROM assembly WHERE idAssembly=?";
    private static final String SQL_UPDATE_ASSEMBLY  = "UPDATE assembly SET `assembly`.name=? WHERE idAssembly=?";
    private static final String SQL_DELETE_ASSEMBLY = "DELETE FROM assembly WHERE idAssembly=?";
    private static final String SQL_ADD_ASSEMBLY = "INSERT INTO assembly(`assembly`.name) VALUES (?)";
    private static final String SQL_ADD_TRACK_TO_ORDER_LIST = "INSERT INTO assembly_list(idAssembly,idAudio_Track) VALUES(?,?)";

    @Override
    public List<MusicAssembly> takeAll() {
        List<MusicAssembly> musicAssemblies = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_ASSEMBLY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MusicAssembly musicAssembly = createMusicAssembly(resultSet);
                musicAssemblies.add(musicAssembly);
            }
            LOGGER.log(Level.INFO, "Received all assemblies from the database");
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to take all assemblies", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return musicAssemblies;
    }

    @Override
    public MusicAssembly take(String id) {
        MusicAssembly musicAssembly = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (id == null || id.isEmpty()) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_ASSEMBLY_BY_ID);
            int idAssembly = Integer.parseInt(id);
            statement.setInt(1, idAssembly);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                musicAssembly = createMusicAssembly(resultSet);
            }
            LOGGER.log(Level.INFO, "Received assembly from the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. id=null or id is empty!", e);
        } catch (NumberFormatException e) {
            LOGGER.error("Unable to convert string to integer", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to take assembly", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return musicAssembly;
    }

    @Override
    public boolean update(MusicAssembly musicAssembly) {
        boolean isUpdated = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (musicAssembly == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_UPDATE_ASSEMBLY);
            statement.setString(1, musicAssembly.getName());
            statement.setInt(2, musicAssembly.getId());
            if(statement.executeUpdate()!=0){
                isUpdated = true;
            }
            LOGGER.log(Level.INFO, "Updated assembly in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. musicAssembly=null!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to update assembly", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isUpdated;
    }

    @Override
    public boolean delete(MusicAssembly musicAssembly) {
        boolean isDeleted = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (musicAssembly == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_DELETE_ASSEMBLY);
            statement.setInt(1, musicAssembly.getId());
            if(statement.executeUpdate()!=0){
                isDeleted = true;
            }
            LOGGER.log(Level.INFO, "Deleted assembly in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. musicAssembly=null!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to delete assembly", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isDeleted;
    }

    /**
     * Create music assembly
     *
     * @param resultSet is data from database
     * @return MusicAssembly object
     * @see MusicAssembly
     */
    private MusicAssembly createMusicAssembly(ResultSet resultSet) throws SQLException {
        MusicAssembly musicAssembly = null;
        try {
            if (resultSet == null) {
                throw new CommonException();
            }
            int id = resultSet.getInt(ASSEMBLY_ID);
            String name = resultSet.getString(NAME);
            musicAssembly = new MusicAssembly(id, name);
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter.", e);
        }
        return musicAssembly;
    }

    /**
     * Add music assembly to database
     *
     * @param musicAssembly is assembly name
     * @return MusicAssembly object
     * @see MusicAssembly
     */
    public MusicAssembly addAssembly(String musicAssembly) {
        MusicAssembly assembly=null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (musicAssembly == null||musicAssembly.isEmpty()) {
                throw new CommonException();
            }
            assembly=checkAndGet(takeAll(),musicAssembly);
            if(assembly==null){
                connection = ConnectionPool.getInstance().takeConnection();
                statement = connection.prepareStatement(SQL_ADD_ASSEMBLY, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, musicAssembly);
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                assembly = new MusicAssembly(resultSet.getInt(1),musicAssembly);
            }
            LOGGER.log(Level.INFO, "Add assembly to the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter.", e);
        }catch(SQLException e) {
            LOGGER.error("SQLException in trying to add assembly", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return assembly;
    }

    /**
     * Add audio track to music assembly
     *
     * @param idAssembly is assembly id
     * @param idAudioTrack is track's id
     * @return {@code true} if audio track is added to assembly. {@code false} if audio track isn't added to assembly.
     * @see MusicAssembly
     */
    public boolean addTrackToAssembly(int idAssembly, int idAudioTrack) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isAdded = false;
        try {
            if (idAssembly<=0||idAudioTrack<=0) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_ADD_TRACK_TO_ORDER_LIST);
            statement.setInt(1, idAssembly);
            statement.setInt(2, idAudioTrack);
            if(statement.executeUpdate()!=0){
                isAdded = true;
            }
            LOGGER.log(Level.INFO, "Add track to assembly list to the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameters.", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to add track to assembly list", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isAdded;
    }

    /**
     * Check existence of music assembly and retrieve if exist
     *
     * @param musicAssemblies is collection of assemblies
     * @param musicAssembly is assembly name
     * @return MusicAssembly object
     * @see MusicAssembly
     */
    private MusicAssembly checkAndGet(List<MusicAssembly> musicAssemblies, String musicAssembly){
        for (int i = 0; i<musicAssemblies.size();i++){
            String currentMusicAssembly= musicAssemblies.get(i).getName();
            if(currentMusicAssembly.equals(musicAssembly)){
                return musicAssemblies.get(i);
            }
        }
        return null;
    }
}
