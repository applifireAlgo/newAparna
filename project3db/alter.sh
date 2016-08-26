




echo $PATH
OSNAME=`uname -s`
DB_PATH=/tmp/applifire/db/UBYNWEI5RY3GNDPJWBKA
ART_CREATE_PATH=/tmp/applifire/db/UBYNWEI5RY3GNDPJWBKA/art/create
AST_CREATE_PATH=/tmp/applifire/db/UBYNWEI5RY3GNDPJWBKA/ast/create
MYSQL=/usr/bin
APPLFIREUSER=root
APPLFIREPASSWORD=root
APPLFIREHOST=localhost

if [ $OSNAME == "Darwin" ]; then
echo "Setting up MYSQL PATH for OS $OSNAME"
MYSQL=/usr/local/mysql/bin/
fi



DB_NAME=project3
USER=project3
PASSWORD=project3
PORT=3306
HOST=localhost


