package com.example.connectinternet.json

//[
//{
//    "house": "Gryffindor",
//    "emoji": "🦁",
//    "founder": "Godric Gryffindor",
//    "colors": [
//    "red",
//    "gold"
//    ],
//    "animal": "Lion",
//    "index": 0
//},
//{
//    "house": "Hufflepuff",
//    "emoji": "🦡",
//    "founder": "Helga Hufflepuff",
//    "colors": [
//    "yellow",
//    "black"
//    ],
//    "animal": "Badger",
//    "index": 1
//},
//{
//    "house": "Ravenclaw",
//    "emoji": "🦅",
//    "founder": "Rowena Ravenclaw",
//    "colors": [
//    "blue",
//    "yellow"
//    ],
//    "animal": "Raven",
//    "index": 2
//},
//{
//    "house": "Slytherin",
//    "emoji": "🐍",
//    "founder": "Salazar Slytherin",
//    "colors": [
//    "green",
//    "silver"
//    ],
//    "animal": "Snake",
//    "index": 3
//}
//]
data class House(
    val house: String,
    val emoji: String,
    val founder: String,
    val colors: List<String>,
    val animal: String,
    val index: Int
)
