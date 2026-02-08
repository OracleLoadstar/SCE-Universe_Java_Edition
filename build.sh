#!/bin/bash
# --- 配置区 ---
MAIN_CLASS="cn.oraclestar.sce.App.App" 
OUT_NAME="app.jar"

echo "Step 1: Cleaning..."
rm -rf bin $OUT_NAME

echo "Step 2: Compiling..."
mkdir -p bin
find src -name "*.java" | xargs javac -d bin -sourcepath src -encoding UTF-8

echo "Step 3: Copying resources..."
rsync -av --exclude="*.java" src/ bin/

echo "Step 4: Packaging..."
jar cfe $OUT_NAME $MAIN_CLASS -C bin .

echo "Done! Run with: java -jar $OUT_NAME"