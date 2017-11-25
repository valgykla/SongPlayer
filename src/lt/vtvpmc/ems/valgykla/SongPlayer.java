package lt.vtvpmc.ems.valgykla;

import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.concurrent.TimeUnit;
import org.lwjgl.openal.*;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.stb.STBVorbis.*;
import static org.lwjgl.system.MemoryStack.stackMallocInt;
import static org.lwjgl.system.MemoryStack.stackPop;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.libc.LibCStdlib.free;

public class SongPlayer
{
    private float spd;
    private Song sng = new Song(null, null);

    public void setSpeed(float speed)
    {
        this.spd = speed;
    }

    public void setSong(final Song song)
    {
        this.sng = song;
    }

    public void play()
    {
        //OpenAL setup
        String defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER); //Getting the device
        long device = alcOpenDevice(defaultDeviceName); //Open the device.

        int[] atributes = {0}; //Set atributes
        long context = alcCreateContext(device, atributes); //Makes a context
        alcMakeContextCurrent(context);

        ALCCapabilities alcCapabilities = ALC.createCapabilities(device);
        ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);

        if(alCapabilities.OpenAL10)
        {
                String sng_file = this.sng.getWorkDir() + this.sng.getTitle() + ".ogg";

                stackPush();
                IntBuffer chanBuff = stackMallocInt(1);
                stackPush();
                IntBuffer sampleRateBuff = stackMallocInt(1);

                ShortBuffer rawAudio = stb_vorbis_decode_filename(sng_file, chanBuff, sampleRateBuff);

                int chnls = chanBuff.get();
                int smpl_rt = sampleRateBuff.get();

                stackPop();
                stackPop();

                int form = -1;

                if (chnls == 1) {
                    form = AL_FORMAT_MONO16;
                } else {
                    form = AL_FORMAT_STEREO16;
                }

                int buff_Point = alGenBuffers();

            try {
                alBufferData(buff_Point, form, rawAudio, smpl_rt); // has an tendency of throwing exceptions

                free(rawAudio); //Release the buffer.


                int srcPnt = alGenSources();

                alSourcei(srcPnt, AL_BUFFER, buff_Point);

                alSourcef(srcPnt, AL_PITCH, (this.spd));

                alSourcef(srcPnt, AL_VELOCITY, (this.spd)); //Nightcore/slowmo mode activated

                alSourcePlay(srcPnt);

            } catch (Exception e) {};

            for(int i=0; i<this.sng.size();i++)
                {
                    //if(new String(this.sng.getLyrics(i)).contains("[skip="))
                    System.out.println("[" + this.sng.getTitle() + "]: " + this.sng.getLyrics(i));
                    try {
                        short sd = (short) (1000/this.spd); // Can't be that big, right?
                        TimeUnit.MILLISECONDS.sleep(sd);
                    } catch (InterruptedException e) {}
                }

            alDeleteBuffers(buff_Point);
            alcDestroyContext(context);
            alcCloseDevice(device);
        }

    }

}