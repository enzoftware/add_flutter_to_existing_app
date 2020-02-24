//
//  ContentView.swift
//  AddFlutter2iOSApp
//
//  Created by Enzo Lizama on 2/23/20.
//  Copyright © 2020 Enzo Lizama. All rights reserved.
//

import SwiftUI
import Flutter
import UIKit



struct QuoteEntity: Codable, Identifiable {
    public var id: String
    
    public var character: String
    public var image: String
    
    enum CodingKeys: String, CodingKey {
        case id = "quote"
        
        case character = "character"
        case image = "image"
    }
}

class FetchQuotes : ObservableObject{
    @Published
    var quotes = [QuoteEntity]()
    init() {
        let url = URL(string: "https://thesimpsonsquoteapi.glitch.me/quotes?count=10")!
        URLSession.shared.dataTask(with: url){(data, response, error) in
            do{
                if let quoteData = data {
                    let decodeData = try JSONDecoder().decode([QuoteEntity].self, from: quoteData)
                    DispatchQueue.main.async {
                        self.quotes = decodeData
                    }
                } else {
                    print("No data")
                }
            }catch{
                print("Error")
            }
            
        }.resume()
    }
}

struct ContentView: View {

    
    @ObservedObject
    var fetch = FetchQuotes()
    
    @State private var isPresented = false

    var body: some View {
        VStack{
            List(fetch.quotes){ quote in
                 VStack(alignment: .leading){
                   Text(quote.character).font(.system(size: 18))
                   .foregroundColor(Color.black)
                   Text(quote.id).font(.system(size: 12))
                   .foregroundColor(Color.black)
                }
                
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
