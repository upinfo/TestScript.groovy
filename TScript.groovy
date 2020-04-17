//Read the input data
def inputValue = "5,8,11:30,12:00"
println "Enter the searching criteria : " + inputValue
def inputValueArray = inputValue.split(",")

def intFloor = inputValueArray[1]
def intSize = inputValueArray[0]
def StartTime = inputValueArray[2]
def EndTime = inputValueArray[3]
def RoomNum = 0
def timeSFound = false
def timeEFound = false
def AvailNextEndTime = EndTime
def NearestEndTime = EndTime

//println(intFloor + " - " + intSize + " - " + StartTime + " - " + EndTime)

//Read the input file
new File("D:\\GroovyTest\\rooms.txt").withReader('UTF-8') { reader ->
    def line
        while ((line = reader.readLine()) != null) { 
            println(line)
            def lineElement = line.split(",")
            //Check Start time available in the row
            lineElement.eachWithIndex { item, index ->
                    if (item == StartTime){
                        //println("Found the Start Time slot")
                        timeSFound = true
                        if (lineElement[1] >= intSize)
                        {
                            //Can fit all meeting members in the team
                            AvailNextEndTime = lineElement[index + 1]
                            //println AvailNextEndTime
                            if (EndTime <= AvailNextEndTime)
                            {
                                if (NearestEndTime == EndTime) 
                                    NearestEndTime = AvailNextEndTime
                                if (AvailNextEndTime <= NearestEndTime)
                                {
                                    NearestEndTime = AvailNextEndTime
                                    RoomNum = lineElement[0]
                                }
                                timeEFound = true
                            }
                        }
                    }
                }
        }
        if (timeSFound && timeEFound)
        {
            println("Found the time shlot")
            println("Please use the floor : " + RoomNum)
        }
        else
        {
            println("No Slot availabe")
        }
    }
