Course of the game:

    1. Server decides how many players are going to play
    2. Players connect to the server
    3. Player's unique ID is sent to them
    4. When all players connect the game starts
    5. Ante is taken from all the players
    6. Players are given 5 cards each
    7. First round of bidding begins
        The player can: 
            -> raise
            -> fold 
            -> check   
    8. Players then choose which cards they want to change
        The player can:
            -> change cards (after writing them such as: 0,1,3)
            -> change no cards by pressing <enter>
    9. The second round of bidding begins analogous to in point 7
    10. After bidding the winner is decided and the whole money pool is given to him
    11. Next round begins
    
Rules:

    -> seniority of hands is like in standard poker
    -> card suit doesn't matter
    -> if multiple players win then money is split equally
    
Starting the game:

    1. run "mvn clear package"
    2. start the server with "java -jar <path_to_server.jar>"
    3. start clients with "java -jar <path_to_client.jar>"
    
Game protocol:

    The server has 2 ways of communication with the client:
        1. send a message and wait for response (1 is sent as the first line of sever message)
        2. send a message and do not wait for response (-1 is the first line of message)
