/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.monitoring.system;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class Monitor {
    public static void Manipulating(String pathString) {
        
         try(WatchService service = FileSystems.getDefault().newWatchService()) {
            Map<WatchKey, Path> keyMap = new HashMap<>();
            Path path = Paths.get(pathString); //path
            keyMap.put(path.register(service, 
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.OVERFLOW), path);
            
            WatchKey watchKey;
            
            do {                
                watchKey = service.take();
                Path eventDir = keyMap.get(watchKey);
                
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path eventPath = (Path)event.context();
                    
                    System.out.println(eventDir + ": " + kind + ": " + eventPath);
                }
            } while (watchKey.reset());
        } catch (Exception e) {
        }
    }
}
