




echo $PATH
DB_PATH=/tmp/applifire/db/I2OYIPSRXHTWVBBKFF1Q
MYSQL=/usr/bin
USER=pron
PASSWORD=pron
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'