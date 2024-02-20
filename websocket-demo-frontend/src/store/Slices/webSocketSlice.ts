import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {RootState} from "../store.ts";
import Stomp from "stompjs";

export enum messageType {
    CHAT= 'CHAT',
    JOIN = 'JOIN',
    LEAVE = 'LEAVE'
}
interface webSocketMessage {
    sender: string;
    content: string;
    timestamp: string;
    type: messageType;
}
interface webSocketState {
    isConnected: boolean;
    stompClient: Stomp.Client | undefined;
    messages: webSocketMessage[] | undefined
    onlinePersons: number;
}

const initialState: webSocketState = {
    isConnected: false,
    stompClient: undefined,
    messages: [],
    onlinePersons: 0,
};

export const webSocketSlice = createSlice({
    name: 'webSocket',
    initialState,
    reducers: {
        setIsConnected: (state, action : PayloadAction<boolean>) => {
            state.isConnected = action.payload;
        },
        appendMessage: (state, action : PayloadAction<webSocketMessage>) => {
            state.messages?.push(action.payload);
        },
        setStompClient: (state, action : PayloadAction<Stomp.Client>) => {
            state.stompClient = action.payload;
        },
        setOnlinePersons: (state, action: PayloadAction<number>) => {
            state.onlinePersons = action.payload;
        },
    },
});

export const {setIsConnected, appendMessage,setStompClient,setOnlinePersons} = webSocketSlice.actions;
export default webSocketSlice.reducer;
export const selectWebSocket = (state: RootState) => state.webSocket;