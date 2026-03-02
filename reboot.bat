@echo off
echo Stopping and removing containers...
docker-compose down -v

echo Starting containers...
docker-compose up -d

echo Done!
pause