if [ -f "commitFile.txt" ]; then
    rm commitFile.txt
    git add -A
    git commit -m "Commiting delete of file..."
    git push origin develop
else
    touch commitFile.txt
    git add commitFile.txt
    git commit -m "Commiting new file..."
    git push origin develop
fi