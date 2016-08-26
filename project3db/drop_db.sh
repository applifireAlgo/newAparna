




echo $PATH
DB_PATH=/tmp/applifire/db/UBYNWEI5RY3GNDPJWBKA
MYSQL=/usr/bin
USER=project3
PASSWORD=project3
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'