#!/bin/sh
db_user=falcon
db_password=phefrebre796ayuf
db_name=falcon
sql_location=.

echo drop database if exists ${db_name} | mysql -u ${db_user} -p${db_password}
echo Database drop. Done.
echo create database ${db_name} | mysql -u ${db_user} -p${db_password}
echo Database create. Done.
mysql -u ${db_user} -p${db_password} ${db_name} < ${sql_location}/${db_name}-structure.sql
echo Structure create. Done.
mysql -u ${db_user} -p${db_password} ${db_name} < ${sql_location}/${db_name}-data.sql
echo Data populate. Done.
echo Done

