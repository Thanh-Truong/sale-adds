package example.config;

import javax.validation.constraints.NotNull;

@SuppressWarnings("UnusedDeclaration")
public class MessagesConfiguration {

    @NotNull
    private String hello;
	@NotNull
	private String instruction;

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
}
