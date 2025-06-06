#!/bin/bash

# Répertoire temporaire
TEMP_DIR="temp"
WEB_INF_DIR="$TEMP_DIR/WEB-INF"
LIB_DIR="$WEB_INF_DIR/lib"
CONTROLLER_DIR="src/mg/p16/java/controller"
SERVLET_DIR="src/mg/p16/java/servlet"
MODEL_DIR="src/mg/p16/java/model"
DAO_DIR="src/mg/p16/java/dao"
CONNEXION_DIR="src/mg/p16/java/connexion"

# Répertoire du projet Tomcat
WEBAPPS_DIR="C:\servertomcat\webapps"
# C:\serveur\webapps

echo "$WEBAPPS_DIR"
# Fichiers
WEB_XML="web.xml"

# Chemins des .jar
FRAMEWORK_JAR="C:/Users/Francky/Documents/Mr_Naina_Projet_en_ligne/Ticketing/AvionTicketing/lib/framework.jar"
LIB_JARS_DIR="C:/Users/Francky/Documents/Mr_Naina_Projet_en_ligne/Ticketing/AvionTicketing/lib/*.jar"

# Étapes

# Suppression du répertoire temporaire s'il existe déjà
if [ -d "$TEMP_DIR" ]; then
    rm -rf "$TEMP_DIR"
    echo "Répertoire temporaire existant supprimé."
fi

# Création du répertoire temporaire
mkdir -p "$TEMP_DIR"
echo "Répertoire temporaire créé."

# Création des sous-répertoires WEB-INF et lib dans temp
mkdir -p "$WEB_INF_DIR"
mkdir -p "$LIB_DIR"
echo "Sous-répertoires WEB-INF et lib créés."

# Copie du répertoire web dans le projet Tomcat
cp -r "web" "$TEMP_DIR"
echo "Répertoire web copié dans le projet Tomcat."

# Copie des fichiers de configuration dans le répertoire temporaire WEB-INF
cp "$WEB_XML" "$WEB_INF_DIR"
echo "Fichier web.xml copié dans le répertoire temporaire WEB-INF."

# Copie du fichier framework.jar dans le répertoire temporaire WEB-INF/lib
cp "$FRAMEWORK_JAR" "$LIB_DIR"
echo "Fichier framework.jar copié dans le répertoire temporaire WEB-INF/lib."

# Copie des fichiers .jar supplémentaires dans le répertoire temporaire WEB-INF/lib
cp $LIB_JARS_DIR "$LIB_DIR"
echo "Fichiers .jar supplémentaires copiés dans le répertoire temporaire WEB-INF/lib."

# Création du répertoire temporaire pour les fichiers Java
mkdir -p "$TEMP_DIR/java_files"
echo "Répertoire temporaire pour les fichiers Java créé."

# Copie de tous les fichiers Java des dossiers controller, servlet et model dans le répertoire temporaire
cp "$CONTROLLER_DIR"/*.java "$TEMP_DIR/java_files"
cp "$MODEL_DIR"/*.java "$TEMP_DIR/java_files"
cp "$DAO_DIR"/*.java "$TEMP_DIR/java_files"
cp "$CONNEXION_DIR"/*.java "$TEMP_DIR/java_files"
echo "Tous les fichiers Java copiés dans le répertoire temporaire."

# Compilation des fichiers Java du répertoire temporaire vers temp/WEB-INF/classes
mkdir -p "$WEB_INF_DIR/classes"
javac -cp "$LIB_DIR/*" -d "$WEB_INF_DIR/classes" "$TEMP_DIR/java_files"/*.java
echo "Fichiers Java compilés dans le répertoire temporaire WEB-INF/classes."

# Nettoyage du répertoire temporaire pour les fichiers Java
rm -rf "$TEMP_DIR/java_files"
echo "Répertoire temporaire java_files supprimé."

# Création du fichier WAR
jar -cvf Framework.war -C "$TEMP_DIR" .
echo "Fichier WAR créé avec succès."

# Copie du fichier WAR vers le répertoire webapps
cp Framework.war "$WEBAPPS_DIR"
echo "Fichier WAR copié dans le répertoire webapps."

echo "Le déploiement est terminé."
