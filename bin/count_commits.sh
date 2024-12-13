
emails=( "t.alves1717@gmail.com" "1221003@isep.ipp.pt" "1221219@isep.ipp.pt" "1220779@isep.ipp.pt" )

names=( "Tiago Alves" "Tiago Santos" "Diogo Araujo" "Simao Lopes" )

len=${#emails[@]}

i=0

while [ $i -lt $len ]
do
	echo -e "${names[$i]}\tcommits: `git log | grep "${emails[$i]}" | wc -l`"
	i=$(( $i + 1 ))
done



#for i in "${emails[@]}"
#do
#	echo "$i commits: `git log | grep "$i" | wc -l`"
#done
