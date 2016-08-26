




echo $PATH
OSNAME=`uname -s`
DB_PATH=/tmp/applifire/db/I2OYIPSRXHTWVBBKFF1Q
ART_CREATE_PATH=/tmp/applifire/db/I2OYIPSRXHTWVBBKFF1Q/art/create
AST_CREATE_PATH=/tmp/applifire/db/I2OYIPSRXHTWVBBKFF1Q/ast/create
MYSQL=/usr/bin
APPLFIREUSER=root
APPLFIREPASSWORD=root
APPLFIREHOST=localhost

if [ $OSNAME == "Darwin" ]; then
echo "Setting up MYSQL PATH for OS $OSNAME"
MYSQL=/usr/local/mysql/bin/
fi



DB_NAME=pron
USER=pron
PASSWORD=pron
PORT=3306
HOST=localhost


