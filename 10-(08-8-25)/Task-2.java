/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [08-08-25]
 | Program Definition : 
 |2. (Use all public , private , Protected and calling them)
 |  Create a class "communicationMethod" with the method sendMessage().
 |  Create a subclass by extending "communicationMethod" , "Digital" and "Physical" and override the sendMessage() method.
 |  Create a subclass "VoiceMessage" , "VideoMessage" , and "TextMessage" by inheriting class Digital also override the method sendMessage().
 |  Create a subclass "byPost" and override method sendMessage().
 -------------------------------------------------------------*/



 // base class
class CommunicationMethod 
{
    // private method - only accessible within this class
    private void internalLog() 
    {
        System.out.println("Logging communication method......");
    }

    // protected method - accessible in this class and all subclasses
    protected void prepare() 
    {
        System.out.println("Preparing to send message...");
    }

    // public method - to be overridden by subclasses
    public void sendMessage() 
    {
        internalLog(); // called within this class
        prepare();     // accessible to subclasses
        System.out.println("Sending message using generic method.");
    }
}

// subclass Digital extends CommunicationMethod
class Digital extends CommunicationMethod 
{
    @Override
    public void sendMessage() 
    {
        prepare(); // protected method from parent
        System.out.println("Sending message digitally.");
    }
}

// subclass Physical extends CommunicationMethod
class Physical extends CommunicationMethod 
{
    @Override
    public void sendMessage() 
    {
        prepare(); // protected method
        System.out.println("Sending message physically.");
    }
}

// Subclass VoiceMessage extends Digital
class VoiceMessage extends Digital 
{
    @Override
    public void sendMessage() 
    {
        prepare();
        System.out.println("Sending voice message.");
    }
}

// Subclass VideoMessage extends Digital
class VideoMessage extends Digital 
{
    @Override
    public void sendMessage() 
    {
        prepare();
        System.out.println("Sending video message.");
    }
}

// subclass TextMessage extends Digital
class TextMessage extends Digital 
{
    @Override
    public void sendMessage() 
    {
        prepare();
        System.out.println("Sending text message.");
    }
}

// subclass byPost extends Physical
class ByPost extends Physical 
{
    @Override
    public void sendMessage() 
    {
        prepare();
        System.out.println("Sending message by post.");
    }
}

// main class
class TASK_2 
{
    public static void main(String[] args) 
    {
        // base class
        CommunicationMethod cm=new CommunicationMethod();
        cm.sendMessage();
        System.out.println();

        // subclasses
        Digital digital=new Digital();
        digital.sendMessage();
        System.out.println();

        Physical physical=new Physical();
        physical.sendMessage();
        System.out.println();

        VoiceMessage voice=new VoiceMessage();
        voice.sendMessage();
        System.out.println();

        VideoMessage video=new VideoMessage();
        video.sendMessage();
        System.out.println();

        TextMessage text=new TextMessage();
        text.sendMessage();
        System.out.println();

        ByPost post=new ByPost();
        post.sendMessage();
    }
}

// output 
/* 
Logging communication method......
Preparing to send message...
Sending message using generic method.

Preparing to send message...
Sending message digitally.

Preparing to send message...
Sending message physically.

Preparing to send message...
Sending voice message.

Preparing to send message...
Sending video message.

Preparing to send message...
Sending text message.

Preparing to send message...
Sending message by post. 
*/