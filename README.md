# Medienverwaltung
Webanwendung mit globalem Katalog zur Verwaltung der privaten Mediensammlung.

# Betreiben der Anwendung

1. Git Clone aus GitHub. 
2. MySQL-Datenbank "media" anlegen.
3. Aktuelle Version des Datenbank-Dumps aus db/ in "media" einspielen. 
4. Verbindungsparamater in src/main/resources/config.properties anpassen.
5. Gegebenenfalls in src/main/resources/ die logback.xml anpassen.
6. Maven Projekt in Eclipse importieren und dort auf lokalem Tomcat installieren.
7. Tomcat starten.
8. Aufrufen: "Pfad_zu_tomcat"/medienverwaltung/profil


**Alternativ:**
Mit Maven bauen, erzeugt "medienverwaltung.war" in target/. Die .war - Datei in das webapps/ - Verzeichnis eines Tomcats kopieren. Gegebenenfalls server.xml anpassen / sonstige Konfigurationsänderungen am Tomcat vornehmen.


