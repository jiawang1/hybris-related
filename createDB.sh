#!/bin/bash
cat hybris/config/local.properties | grep "db." | sed 's/\./_/g' > .temp.properties
. .temp.properties
rm .temp.properties

# echo ${db_name}
# echo ${db_username}
# echo ${db_password}

mysql -u root -p -e "DROP DATABASE IF EXISTS ${db_name}; CREATE DATABASE ${db_name} CHARACTER SET=utf8 COLLATE=utf8_bin; GRANT ALL PRIVILEGES ON ${db_name}.* TO ${db_username}@localhost IDENTIFIED BY '${db_password}'; "
